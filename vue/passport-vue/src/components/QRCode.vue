
<style scoped>
</style>


<template>
  <div id="the-code" ref="the_code"></div>
</template>


<script>
import QRCode from "qrcode-svg";

export default {
  name: "QRCode",

  props: {
    content: String,
    padding: Number,
    width: Number,
    height: Number,
    color: String,
    background: String,
    ec1: String
  },

  data() {
    return {};
  },

  created() {},

  mounted() {
    this.refresh();
  },

  watch: {
    content(theNewValue, theOldValue) {
      this.refresh();
    }
  },

  methods: {
    refresh() {
      var param = {};
      param.ec1 = "M";

      this.initField("content", param, this, "null");
      this.initField("padding", param, this, 2);
      this.initField("width", param, this, 256);
      this.initField("height", param, this, 256);
      this.initField("color", param, this, "#000000");
      this.initField("background", param, this, "#ffffff");

      var svg = new QRCode(param).svg();
      var code = this.$refs.the_code;
      code.innerHTML = svg;
    },

    initField(name, dest, src, default_value) {
      var value = src[name];
      if (value == null) {
        value = default_value;
      }
      dest[name] = value;
    }
  }
};
</script>
