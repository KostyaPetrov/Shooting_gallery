<template>
  <el-form ref="plotForm" :model="plotForm" :rules="rules" label-width="120px">
    <el-form-item label="Ось X" prop="x_axis">
      <el-select v-model="plotForm.x_axis" clearable placeholder="Ось X">
        <el-option v-for="item in x_axis_options" :key="item.value" :label="item.label" :value="item.value" />
      </el-select>
    </el-form-item>

    <el-form-item label="Ось Y" prop="y_axis">
      <el-input-number v-model="plotForm.y_axis" :min="-3" :max="3" :step="1" step-strictly></el-input-number>
    </el-form-item>

    <el-form-item label="Радиус" prop="radius">
      <el-select v-model="plotForm.radius" clearable placeholder="Радиус">
        <el-option v-for="item in radius_options" :key="item.value" :label="item.label" :value="item.value" />
      </el-select>
    </el-form-item>

    <el-row>
      <el-col :span="12">
        <el-form-item>
          <el-button type="primary" :loading="is_loading" @click="submitForm('plotForm')">

            Рассчитать
          </el-button>
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item>
          <el-button type="danger" @click="reset()">Сброс</el-button>
        </el-form-item>
      </el-col>
    </el-row>

  </el-form>
</template>

<script>
export default {
  name: "v-controls",
  data() {
    return {
      is_loading: false,
      x_axis_options: [
        {value: '3', label: '3'},
        {value: '2', label: '2'},
        {value: '1', label: '1'},
        {value: '0', label: '0'},
        {value: '-1', label: '-1'},
        {value: '-2', label: '-2'},
        {value: '-3', label: '-3'},
        {value: '-4', label: '-4'},
        {value: '-5', label: '-5'},
      ],
      radius_options: [
        {value: '3', label: '3'},
        {value: '2', label: '2'},
        {value: '1', label: '1'},
        {value: '0', label: '0'},
        {value: '-1', label: '-1'},
        {value: '-2', label: '-2'},
        {value: '-3', label: '-3'},
        {value: '-4', label: '-4'},
        {value: '-5', label: '-5'},
      ],
      plotForm: {
        x_axis: '',
        y_axis: 0,
        radius: '',
      },
      rules: {
        x_axis: [{ required: true, message: 'Необходимо указать значение оси X', trigger: 'change' }],
        y_axis: [{ required: true, message: 'Необходимо указать значение оси Y', trigger: 'change' }],
        radius: [{ required: true, message: 'Необходимо указать значение радиуса', trigger: 'change' }]
      }
    }
  },
  methods: {
    setIsLoading(val) {
      this.$data.is_loading = val
    },
    reset() {
      this.plotForm.x_axis = null
      this.plotForm.y_axis = null
      this.plotForm.radius = null
      this.$data.is_loading = false
    },
    submitForm(formName) {
      this.$data.is_loading = true

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$emit('sendData', this.plotForm.x_axis, this.plotForm.y_axis, this.plotForm.radius)
        }
      });
    },
  }
}
</script>

<style scoped>

</style>