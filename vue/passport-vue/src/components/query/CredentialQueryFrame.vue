
<style scoped>
.main-box {
  display: block;
}

.main-left {
  display: inline-block;
  width: 60%;
  height: 100%;
  margin-top: 0px;
  margin-bottom: 0px;
}

.main-right {
  display: inline-block;
  width: 40%;
  height: 100%;
}

.el-box {
  padding: 0px;
}

.h100 {
  height: 100%;
}
</style>

<template>
  <div>
    <el-container>
      <el-header class="el-box">
        <DomainBox class="h100" />
      </el-header>
      <el-container>
        <el-aside class="el-box" width="200px">
          <ListBox />
        </el-aside>
        <el-main class="el-box">
          <div class="main-box">
            <div class="main-left">
              <InfoBox />
            </div>
            <div class="main-right">
              <ScanBox />
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import DomainBox from "./CredentialDomainBox.vue";
import ListBox from "./CredentialListBox.vue";
import InfoBox from "./CredentialInfoBox.vue";
import ScanBox from "./CredentialScanBox.vue";

export default {
  name: "QueryFrame",
  components: { DomainBox, ListBox, InfoBox, ScanBox }
};
</script>