const path = require('path')

module.exports = {
  outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
  indexPath: "../templates/index.html",
  externals: ['axios'],

  devServer: {
    proxy: {
      '/api':{
        target: 'http://localhost:9000',
        ws: true,
        changeOrigin: true
      },
    }
  }
}
//axios 에서 활용
//axios.get('/api',{
//     	headers: {
//     		'Access-Control-Allow-Origin': '*',
//     		'Content-Type': 'application/json; charset = utf-8'
//     	}
//     })
//     .then(response => {
//     	console.log(response.data);
//     })
//     .catch(e => {
//     	console.log('error : ', e)
//     })
