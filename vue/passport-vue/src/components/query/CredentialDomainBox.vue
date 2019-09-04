<style scoped>
.box {
  background: lightblue;
}

.h1title {
  display: inline;
}

.btn-query {
  float: right;
}
</style>

<template>
  <div class="box">
    <div class="view-input-mode" v-show=" displayMode==displayModeInput ">
      <el-input v-model="urlInput">
        <div slot="append">
          <el-button icon="el-icon-search" @click="onClickOk">确定</el-button>
          <el-button icon="el-icon-search" @click="onClickCancel">取消</el-button>
        </div>
      </el-input>
    </div>
    <div class="view-normal-mode" v-show=" displayMode==displayModeNormal ">
      <h1 class="h1title" @dblclick="onClickInput">{{url}}</h1>
      <el-button class="btn-query" @click="onClickInput">Q</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DomainBox",

  props: {
    domain: String
  },

  data() {
    return {
      displayMode: "normal",
      displayModeInput: "input",
      displayModeNormal: "normal",

      url: "https://bing.com/",
      urlInput: "https://bing.com/"
    };
  },

  methods: {
    onClickInput() {
      this.displayMode = this.displayModeInput;
      this.urlInput = this.url;
    },

    onClickCancel() {
      this.displayMode = this.displayModeNormal;
    },

    onClickOk() {
      this.displayMode = this.displayModeNormal;
      this.url = this.urlInput;

      var domain = this.url;
      var account = "tester@test.com";
      this.$store.dispatch("selectCredentials", { domain, account });
    }
  }
};
</script>