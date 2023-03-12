<template>
  <canvas class="area" ref="graph" id="graph" @click="canvasClick" height="300" width="300"></canvas>

</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "v-canvas",
  data() {
    return {
      hatchWidth: 5,
      hatchGap: 40,
      baseHatchGap: 30,
      defaultRadius: 1,
    }
  },
  computed: {
    canvas () {
      return this.$refs.graph
    },
    ctx () {
      return this.canvas.getContext('2d')
    },
    width () {
      return this.canvas.width
    },
    height () {
      return this.canvas.height
    },
    origin () {
      return this.width / 2
    },
    axisFontSize() {
      return this.baseHatchGap / 2
    },
    letterFontSize() {
      return this.hatchGap / 3;
    }
  },
  mounted() {
    this.initPlot()

    if (this.IS_AUTHED() && !this.TABLE_DATA().length)
      this.GET_ALL_DATA().then(result => {this.preDrawPoints(result.data)});
    else
      this.preDrawPoints(this.TABLE_DATA())
  },
  methods: {
    ...mapGetters(['TABLE_DATA', 'IS_AUTHED']),
    ...mapActions(['GET_ALL_DATA']),
    initPlot() {
      this.ctx.clearRect(0, 0, this.width, this.height);

      this.ctx.lineWidth = 2;
      this.ctx.strokeStyle = 'black';

      this.drawXAxis()
      this.drawYAxis()

      this.drawRectangle(
          this.origin - 2 * this.hatchGap, this.origin - this.hatchGap,
          this.origin, this.origin
      )
      this.drawTriangle(
          this.origin, this.origin - 2 * this.hatchGap,
          this.origin + this.hatchGap, this.origin,
          this.origin, this.origin,
      )
      this.drawCircularSector(this.origin, this.origin, this.hatchGap, 0.5 * Math.PI, 0)
    },
    drawXAxis() {
      this.ctx.beginPath();
      this.ctx.moveTo(this.width, this.origin);
      this.ctx.lineTo(this.width - 15, this.origin - 10);
      this.ctx.moveTo(this.width, this.origin);
      this.ctx.lineTo(this.width - 15, this.origin + 10);
      this.ctx.moveTo(this.width, this.origin);
      this.ctx.lineTo(0, this.origin);
      this.ctx.stroke();
      this.ctx.closePath();

      this.ctx.beginPath();
      this.ctx.moveTo(this.origin - this.hatchGap, this.origin - this.hatchWidth);
      this.ctx.lineTo(this.origin - this.hatchGap, this.origin + this.hatchWidth);
      this.ctx.moveTo(this.origin - this.hatchGap * 2, this.origin - this.hatchWidth);
      this.ctx.lineTo(this.origin - this.hatchGap * 2, this.origin + this.hatchWidth);
      this.ctx.moveTo(this.origin + this.hatchGap, this.origin - this.hatchWidth);
      this.ctx.lineTo(this.origin + this.hatchGap, this.origin + this.hatchWidth);
      this.ctx.moveTo(this.origin + this.hatchGap * 2, this.origin - this.hatchWidth);
      this.ctx.lineTo(this.origin + this.hatchGap * 2, this.origin + this.hatchWidth);
      this.ctx.stroke();
      this.ctx.closePath();

      this.ctx.fillStyle = 'black';

      this.ctx.font = `500 ${this.axisFontSize * 1.4}px Roboto`;
      this.ctx.fillText('x', this.width - 20, this.origin - this.hatchWidth * 2.8)

      this.ctx.font = `800 ${this.letterFontSize}px Roboto`;
      this.ctx.fillText('R/2', this.origin + this.hatchGap - 8, this.origin + this.hatchWidth * 3.2);
      this.ctx.fillText('R', this.origin + this.hatchGap * 2 - 3, this.origin + this.hatchWidth * 3.2);
      this.ctx.fillText('-R/2', this.origin - this.hatchGap - 12, this.origin + this.hatchWidth * 3.2);
      this.ctx.fillText('-R', this.origin - this.hatchGap * 2 - 8, this.origin + this.hatchWidth * 3.2);
    },
    drawYAxis() {
      this.ctx.beginPath();
      this.ctx.moveTo(this.origin, 0);
      this.ctx.lineTo(this.origin - 10, 15);
      this.ctx.moveTo(this.origin, 0);
      this.ctx.lineTo(this.origin + 10, 15);
      this.ctx.moveTo(this.origin, 0);
      this.ctx.lineTo(this.origin, this.height);

      this.ctx.stroke();
      this.ctx.closePath();

      this.ctx.beginPath();
      this.ctx.moveTo(this.origin - this.hatchWidth, this.origin - this.hatchGap);
      this.ctx.lineTo(this.origin + this.hatchWidth, this.origin - this.hatchGap);
      this.ctx.moveTo(this.origin - this.hatchWidth, this.origin - this.hatchGap * 2);
      this.ctx.lineTo(this.origin + this.hatchWidth, this.origin - this.hatchGap * 2);
      this.ctx.moveTo(this.origin - this.hatchWidth, this.origin + this.hatchGap);
      this.ctx.lineTo(this.origin + this.hatchWidth, this.origin + this.hatchGap);
      this.ctx.moveTo(this.origin - this.hatchWidth, this.origin + this.hatchGap * 2);
      this.ctx.lineTo(this.origin + this.hatchWidth, this.origin + this.hatchGap * 2);

      this.ctx.stroke();
      this.ctx.closePath();

      this.ctx.fillStyle = 'black';

      this.ctx.font = `500 ${this.axisFontSize * 1.4}px Roboto`;
      this.ctx.fillText('y', this.origin - this.hatchWidth * 4.3, 15)

      this.ctx.font = `800 ${this.letterFontSize}px Roboto`;
      this.ctx.fillText('R/2', this.origin + this.hatchWidth * 2, this.origin - this.hatchGap + 3);
      this.ctx.fillText('R', this.origin + this.hatchWidth * 2, this.origin - this.hatchGap * 2 + 3);
      this.ctx.fillText('-R/2', this.origin + this.hatchWidth * 2, this.origin + this.hatchGap + 3);
      this.ctx.fillText('-R', this.origin + this.hatchWidth * 2, this.origin + this.hatchGap * 2 + 3);
    },
    drawRectangle(x0, y0, x1, y1) {

      this.ctx.fillStyle = 'rgba(80,92,236,0.33)';
      this.ctx.beginPath();

      this.ctx.lineTo(x0, y0);
      this.ctx.lineTo(x0, y1);
      this.ctx.lineTo(x1, y1);
      this.ctx.lineTo(x1, y0);
      this.ctx.lineTo(x0, y0);

      this.ctx.fill();
      this.ctx.strokeStyle = 'blue';
      this.ctx.stroke();
      this.ctx.closePath();
    },
    drawTriangle(x0, y0, x1, y1, x2, y2) {

      this.ctx.fillStyle = 'rgba(80,92,236,0.33)';
      this.ctx.beginPath();

      this.ctx.lineTo(x0, y0);
      this.ctx.lineTo(x1, y1);
      this.ctx.lineTo(x2, y2);
      this.ctx.lineTo(x0, y0);

      this.ctx.fill();
      this.ctx.strokeStyle = 'blue';
      this.ctx.stroke();
      this.ctx.closePath();
    },
    drawCircularSector(x, y, radius, startAngle, endAngle) {
      this.ctx.fillStyle = 'rgba(80,92,236,0.33)';
      this.ctx.beginPath();

      this.ctx.moveTo(x, y)
      this.ctx.arc(x, y, radius, startAngle, endAngle, true);

      this.ctx.fill();
      this.ctx.strokeStyle = 'blue';
      this.ctx.stroke();
      this.ctx.closePath();
    },
    drawPointOnGraph(xCenter, yCenter, rValue, isHit) {
      this.ctx.fillStyle = isHit ? 'ForestGreen' : 'red'

      let x = this.width / 2 + xCenter * this.hatchGap * (2 / rValue) - 3;
      let y = this.height / 2 - yCenter * this.hatchGap * (2 / rValue) - 3;
      this.ctx.fillRect(x, y, this.hatchGap / 10, this.hatchGap / 10);
    },
    getMousePosition(event) {
      return {
        x: event.offsetX * this.width / this.canvas.clientWidth | 0,
        y: event.offsetY * this.height / this.canvas.clientHeight | 0
      };
    },
    preDrawPoints(data, radius = null) {
      for (const element of data) {
        this.drawPointOnGraph(element.x_axis, element.y_axis, radius || element.radius, element.is_hit)
      }
    },
    canvasClick(event) {
      const clickPosition = this.getMousePosition(event)
      const xCenter = Math.round((clickPosition.x - this.width / 2) / (this.hatchGap * (2 / this.defaultRadius)) * 1000) / 1000
      const yCenter = Math.round((this.height / 2 - clickPosition.y) / (this.hatchGap * (2 / this.defaultRadius)) * 1000) / 1000

      this.$emit('sendData', xCenter, yCenter, this.defaultRadius)
    }
  }
}
</script>

<style scoped>
  .area {
    background-color: white;
  }
</style>