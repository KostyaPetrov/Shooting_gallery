import { createStore } from 'vuex'
import axios from "axios";

// const CREATED_CODE = 201
const SUCCESS_CODE = 200

let store = createStore({
    state () {
        return {
            authData: null,
            tableData: Array(),
        }
    },
    mutations: {
        SET_AUTH_DATA (state, login, password) {
            state.authData = {
                username: login,
                password: password
            }
        },
        ADD_TABLE_DATA (state, value) {
            state.tableData.push({
                x_axis: value.x,
                y_axis: value.y,
                radius: value.radius,
                is_hit: value.is_hit,
            })
        },
        SET_TABLE_DATA (state, value) {
            state.tableData = value.map(item => ({
                x_axis: item.x,
                y_axis: item.y,
                radius: item.radius,
                is_hit: item.is_hit,
            }))
        },
    },
    actions: {
        LOGIN({commit}, payload) {
            return new Promise((resolve, reject) => {
                axios.post(process.env.VUE_APP_BACKEND_URL + '/login', {}, {
                    auth: {
                        username: payload.login,
                        password: payload.password
                    }
                })
                .then((response) => {
                    commit('SET_AUTH_DATA', payload.login, payload.password)
                    resolve(response.status === SUCCESS_CODE)
                })
                .catch((response) => {
                    console.log(response)
                    console.log(response.response.data)
                    reject(false)
                })
            })
        },
        SIGN_UP({commit}, payload) {
            return new Promise((resolve, reject) => {
                axios.post(process.env.VUE_APP_BACKEND_URL + '/users', {
                    login: payload.login,
                    password: payload.password
                })
                .then((response) => {
                    if (response.status === SUCCESS_CODE){
                        commit('SET_AUTH_DATA', payload.login, payload.password)
                        resolve({isSuccess: true, msg: ""})
                    } else {
                        console.log(response.data)
                        resolve({isSuccess: false, msg: response.data})
                    }
                })
                .catch((response) => {
                    console.log(response.response.data)
                    reject({isSuccess: false, msg: response.response.data})
                })
            })
        },
        SEND_DATA({commit}, payload) {
            return new Promise((resolve, reject) => {
                axios.post(process.env.VUE_APP_BACKEND_URL + '/compile', {
                    "x": payload.x,
                    "y": payload.y,
                    "radius": payload.radius
                }, {auth: this.authData})
                .then((response) => {
                    if (response.status === SUCCESS_CODE){
                        commit('ADD_TABLE_DATA', response.data)
                        resolve({isSuccess: true, data: response.data})
                    } else {
                        console.log(response.data)
                        resolve({isSuccess: false, data: response.data})
                    }
                })
                .catch((response) => {
                    console.log(response.response.data)
                    reject({isSuccess: false, data: response.response.data})
                })
            })
        },
        GET_ALL_DATA({commit}) {
            return new Promise((resolve, reject) => {
                axios.get(process.env.VUE_APP_BACKEND_URL + '/all-data', {auth: this.authData})
                .then((response) => {
                    if (response.status === SUCCESS_CODE){
                        commit('SET_TABLE_DATA', response.data)
                        resolve({isSuccess: true, data: this.state.tableData})
                    } else {
                        resolve({isSuccess: false, data: this.state.tableData})
                    }
                })
                .catch((response) => {
                    console.log(response.status + " - " + response.response.data)
                    reject({isSuccess: false, data: this.state.tableData})
                })
            })
        },
    },
    getters: {
        IS_AUTHED(state) {
            return Boolean(state.authData)
        },
        TABLE_DATA(state) {
            return state.tableData
        }
    }
})

export default store;