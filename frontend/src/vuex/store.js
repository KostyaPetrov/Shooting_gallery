import { createStore } from 'vuex'
import axios from "axios";

const CREATED_CODE = 201
const SUCCESS_CODE = 200

let store = createStore({
    state () {
        return {
            accessToken: null,
            tableData: Array(),
        }
    },
    mutations: {
        SET_ACCESS_TOKEN (state, accessToken) {
            state.accessToken = accessToken
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
                axios.post(process.env.VUE_APP_BACKEND_URL + '/login', {
                    email: payload.login,
                    password: payload.password
                })
                    .then((response) => {
                        commit('SET_ACCESS_TOKEN', response.data.accessToken)
                        resolve(response.status === 200)
                    })
                    .catch((response) => {
                        console.log(response.response.data)
                        reject(false)
                    })
            })
        },
        SIGN_UP({commit}, payload) {
            return new Promise((resolve, reject) => {
                axios.post(process.env.VUE_APP_BACKEND_URL + '/users', {
                    email: payload.login,
                    password: payload.password
                })
                    .then((response) => {
                        if (response.status === CREATED_CODE){
                            commit('SET_ACCESS_TOKEN', response.data.accessToken)
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
                    "radius": payload.radius,
                    "is_hit": Boolean(payload.x % 2)  // TODO: заменить!
                })
                    .then((response) => {
                        if (response.status === CREATED_CODE){
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
                axios.get(process.env.VUE_APP_BACKEND_URL + '/all-data')
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
        ACCESS_TOKEN(state) {
            return state.accessToken
        },
        TABLE_DATA(state) {
            return state.tableData
        }
    }
})

export default store;