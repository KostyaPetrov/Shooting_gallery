<template>
  <div class="v-main-wrapper">
    <el-container>
      <el-header class="header">
        <el-row class="header-content">
          <el-col :span="8"><div class="grid-content" />
            Petrov Konstantin
          </el-col>
          <el-col :span="8"><div class="grid-content" />
            Group P32081
          </el-col>
          <el-col :span="8"><div class="grid-content" />
            Option 2363
          </el-col>
        </el-row>
      </el-header>
      <el-main class="main-content">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'v-main-wrapper',
  components: {},
  props: {},
  data() {
    return {
      title: 'Main wrapper'
    }
  },
  computed: {},
  methods: {},
  watch: {}
}
</script>

<style scoped>
.header {
  background-color: #a7d1ff;
}
.header-content {
  align-items: center;
}

.main-content{
  align-items: center;
}
</style>