<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import request from 'request'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  mounted () {
    request('http://localhost:8080/api/boards', function (error, response, body) {
      window.console.log('error', error)
      window.console.log('statusCode', response && response.statusCode)
      window.console.log('body', body)
    })
  }
}
</script>
