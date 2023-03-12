<template>
  <div class="v-main">
    <el-row type="flex" justify="end">
      <el-col :span="2">
        <router-link :to="{name: 'sign-in'}">
          <el-link @click="cleanUpLogin">Log out</el-link>
        </router-link>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12" :offset="6">
        <el-alert v-show="is_success_send_data === false" @close="setIsSuccessSendData(null)"
                  title="Ошибка при входе"
                  type="error">
        </el-alert>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="4"><v-canvas ref="canvas" @send-data="sendData"/></el-col>
      <el-col :span="8"><v-controls ref="controls" @send-data="sendData" @clean-up-data="cleanUpData"/></el-col>
      <el-col :span="12"><v-table ref="table"/></el-col>
    </el-row>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import router from "@/router/router";
import VCanvas from "@/components/v-main-components/v-canvas.vue";
import VControls from "@/components/v-main-components/v-controls.vue";
import VTable from "@/components/v-main-components/v-table.vue";

export default {
  name: "v-main",
  components: {VTable, VControls, VCanvas},
  data() {
    return {
      is_success_send_data: null,
    }
  },
  methods: {
    ...mapActions(['SEND_DATA', 'DELETE_ALL_DATA', 'LOG_OUT']),
    ...mapGetters(['IS_AUTHED']),
    setIsSuccessSendData(value) {
      this.is_success_send_data = value
    },
    cleanUpLogin() {
      this.LOG_OUT().then()
    },
    sendData(x, y, radius) {
      const self = this

      this.SEND_DATA({x: x, y: y, radius: radius})
        .then(
          result => {
            this.$refs.controls.setIsLoading(false)

            if (result) {
              this.$refs.canvas.drawPointOnGraph(x, y, radius, result.data.is_hit)
              this.$refs.table.refreshData()
            } else {
              self.setIsSuccessSendData(false)
            }
          },
          () => {
            this.$refs.controls.setIsLoading(false)
            self.setIsSuccessSendData(false)
          }
        );
    },
    cleanUpData() {
      const self = this

      this.DELETE_ALL_DATA()
        .then(
          result => {
            this.$refs.controls.setIsLoading(false)

            if (result) {
              this.$refs.canvas.initPlot()
              this.$refs.table.deleteData()
            } else {
              self.setIsSuccessSendData(false)
            }
          },
          () => {
            this.$refs.controls.setIsLoading(false)
            self.setIsSuccessSendData(false)
          }
        );
    }
  },
  mounted() {
    if (!this.IS_AUTHED()) {
      router.push("sign-in")
    }
  },
}
</script>

<style scoped>

</style>