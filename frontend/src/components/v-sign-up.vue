<template>
  <div class="v-sign-up">
    <el-row>

      <el-col :span="12" :offset="6">
        <el-alert v-show="is_success_login === false" @close="setIsSuccessLogin(null)" type="error">
          {{ errorAlertTitle }}
        </el-alert>
      </el-col>

      <el-col :span="16" :offset="3">
        <el-form ref="signUpForm" status-icon :model="signUpForm" :rules="rules" label-width="150px">

          <el-form-item label="Логин" prop="login">
            <el-input v-model="signUpForm.login" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="Пароль" prop="password">
            <el-input v-model="signUpForm.password" type="password" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="Повторите пароль" prop="checkPassword">
            <el-input v-model="signUpForm.checkPassword" type="password" autocomplete="off"></el-input>
          </el-form-item>

          <el-row>
            <el-col :span="6">
              <el-form-item>
                <el-button type="primary" :loading="is_loading" @click="submitForm('signUpForm')">Зарегистрироваться</el-button>
              </el-form-item>
            </el-col>

            <el-col :span="6" :offset="1">
              <el-form-item>
                <el-button type="danger" @click="goToLogin()">Отмена</el-button>
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
  name: "v-sign-up",
  data() {
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Необходимо указать пароль'));
      } else {
        if (this.signUpForm.checkPassword !== '') {
          this.$refs.signUpForm.validateField('checkPassword');
        }
        callback();
      }
    };
    let validateCheckPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Необходимо повторить пароль'));
      } else if (value !== this.signUpForm.password) {
        callback(new Error('Пароли не совпадают'));
      } else {
        callback();
      }
    };
    return {
      is_success_login: null,
      is_loading: false,
      errorAlertTitle: "",
      signUpForm: {
        login: '',
        password: '',
        checkPassword: '',
      },
      rules: {
        login: [{ required: true, message: 'Необходимо указать Логин', trigger: 'change' }],
        password: [{ required: true,  validator: validatePassword, trigger: 'blur' }],
        checkPassword: [{ required: true,  validator: validateCheckPassword, trigger: 'blur' }]
      }
    }
  },
  methods: {
    ...mapActions([
      'SIGN_UP'
    ]),
    goToLogin() {
      router.push("sign-in")
    },
    setIsSuccessLogin(value) {
      this.is_success_login = value
    },
    setAlertTitle(value) {
      if (value === "Email already exists"){
        this.errorAlertTitle = "Пользователь с таким логином уже существует"
      } else {
        this.errorAlertTitle = "Ошибка при регистрации"
      }
    },
    submitForm(formName) {
      this.$data.is_loading = true

      let self = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.SIGN_UP({login: this.signUpForm.login, password: this.signUpForm.password})
              .then(
                  result => {
                    self.$data.is_loading = false
                    if (result.isSuccess) {
                      router.push("main")
                    } else {
                      self.setAlertTitle(result.msg)
                      self.setIsSuccessLogin(false)
                    }
                  },
                  error => {
                    self.$data.is_loading = false
                    self.setAlertTitle(error.msg)
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