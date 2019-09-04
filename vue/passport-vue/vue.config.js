// vue.config.js
module.exports = {
  devServer: {
    proxy: {
      '/app.api': {
        target: 'http://localhost:8081/',
        ws: false,
        changeOrigin: false
      },

      '/web.api': {
        target: 'http://localhost:8081/',
        ws: false,
        changeOrigin: false
      },

    }
  },

  baseUrl: undefined,
 // outputDir: './../../java/planet/src/main/resources/public',
  assetsDir: undefined,
  runtimeCompiler: undefined,
  productionSourceMap: undefined,
  parallel: undefined,
  css: undefined
};