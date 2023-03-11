import { createRouter, createWebHistory } from "vue-router"

import vSignIn from "../components/v-sign-in.vue"
import vSignUp from "../components/v-sign-up.vue"
import vMain from "../components/v-main.vue"

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            alias: '/sign-in',
            name: "sign-in",
            component: vSignIn
        },
        {
            path: "/sign-up",
            name: "sign-up",
            component: vSignUp
        },
        {
            path: "/main",
            name: "main",
            component: vMain
        }
    ]
})

export default router;
