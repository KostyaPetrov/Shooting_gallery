<template>
      <div class="v-sign-in">
        <el-row>
          <el-col :span="12" :offset="6">
            <el-alert v-show="is_success_login === false" @close="setIsSuccessLogin(null)"
                title="Ошибка при входе"
                type="error">
            </el-alert>
          </el-col>
          <el-col :span="12" :offset="6">
            <el-form ref="loginForm" :model="loginForm" :rules="rules" label-width="120px">

              <el-form-item label="Логин" prop="login">
                <el-input v-model="loginForm.login"></el-input>
              </el-form-item>

              <el-form-item label="Пароль" prop="password">
                <el-input v-model="loginForm.password" type="password"></el-input>
              </el-form-item>

              <el-row>
                <el-col :span="6">
                  <el-form-item>
                    <el-button type="primary" :loading="is_loading" @click="submitForm('loginForm')">Войти</el-button>
                  </el-form-item>
                </el-col>

                <el-col :span="6" :offset="4">
                  <el-form-item>
                    <el-link @click="goToSignUp()">Зарегистрироваться</el-link>
                  </el-form-item>
                </el-col>
              </el-row>

            </el-form>
          </el-col>
        </el-row>
      </div>
</template>

<script>
import {mapActions} from "vuex";
import router from "@/router/router";

export default {
  name: "v-sign-in",
  data() {
    return {
      is_success_login: null,
      is_loading: false,
      loginForm: {
        login: '',
        password: '',
      },
      rules: {
        login: [
          { required: true, message: 'Необходимо указать Логин', trigger: 'change' }
        ],
        password: [
          { required: true, message: 'Необходимо указать Пароль', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    ...mapActions(['LOGIN']),
    setIsSuccessLogin(value) {
      this.is_success_login = value
    },
    goToSignUp() {
      router.push("sign-up")
    },
    submitForm(formName) {
      this.$data.is_loading = true

      let self = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.LOGIN({login: this.loginForm.login, password: this.loginForm.password})
              .then(
                  result => {
                    this.$data.is_loading = false
                    if (result) {
                      router.push("main")
                    } else {
                      self.setIsSuccessLogin(false)
                    }
                  },
                  () => {
                    this.$data.is_loading = false
                    self.setIsSuccessLogin(false)
                  }
              );
        }
      });
    },
  }
}
</script>

<style scoped>

</style>