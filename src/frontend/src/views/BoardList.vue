<template>

  <div>
    <div id="showboards">
      <div v-if="hasResult">

        <div v-for="board in boards" v-bind:key="board.id">
          {{ board.id }}
          <h1>안녕</h1>
        </div>
      </div>
      <button v-else v-on:click="getBoards">글 불러오기</button>

    </div>

  </div>
</template>
<script>
const axios = require('axios')

export default {

  data: function () {
    return {
      boards: []
    }
  },
  computed: {
    hasResult: function () {
      return this.boards.length > 0
    }
  },
  methods: {
    getBoards: function () {
      const baseURI = ''
      axios.get(`${baseURI}/api/boards`)
        .then((result) => {
          console.log(result)
          this.boards = result.data
        })
    }
  }
}
</script>
