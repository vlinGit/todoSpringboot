<script setup lang="ts">
    import task from "./task.vue";
    import {onBeforeMount, onMounted, ref} from 'vue';
    import axios from 'axios';

    export interface TaskItem {
        id: number;
        name: string;
        done: boolean;
    }

    const tasks = ref<{[key: string]:TaskItem}>({});
    const taskCounter = ref(1);

    const getTaskCounter = () => {
        axios.get("http://localhost:8081/api/v1/tasks/latestId")
            .then(res => {
                taskCounter.value = res.data;
            })
    }

    const getTasks = () => {
        axios.get("http://localhost:8081/api/v1/tasks")
            .then(res => {
                const data: Object = res.data;
                for (const [key, value] of Object.entries(data)){
                    tasks.value[value.id] = {id: value.id, name: value.name, done: value.done} as TaskItem;
                    console.log(data);
                }
            })
            .catch(err => {
                console.log(err);
            })
    };

    const addTask = () => {
        if (Object.keys(tasks.value).length < 15){
            const formData = {"id": taskCounter.value, "name": "New Task"};
            axios.post("http://localhost:8081/api/v1/tasks/newTask", formData)
                .then(res => { 
                    tasks.value[String(taskCounter.value)] = res.data;
                    taskCounter.value += 1;
                    console.log(res);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    };

    // index 0 is id, 1 is name
    const removeTask = (data: string[]) => {
        const id: string = data[0]
        axios.delete("http://localhost:8081/api/v1/tasks/" + id)
            .then(res => {
                delete tasks.value[data[0]];
            })
            .catch(err => {
                console.log(err);
            });
    };

    // index 0 is id, 1 is name
    const updateTask = (data: any[]) => {
        const id: string = data[0];
        const newName: string = data[1];
        const done: boolean = data[2];
        const formData = {"id": id, "name": newName.replace("<br >", " ").replace("<br>", " "), "done": done};

        axios.put("http://localhost:8081/api/v1/tasks/updateTask", formData)
            .then(res => {
                const data: TaskItem = res.data;
                tasks.value[data.id] = data;
            })
            .catch(err => {
                console.log(err);
            })
    }

    onMounted(() => {getTasks(); getTaskCounter();});
</script>

<template>
    <div @load="getTasks" id="box">
        <div @click="addTask" id="add">+</div>
        <div id="title">Tasks</div>
        <div id="tasks">
            <div v-for="(taskItem, taskId) in tasks" :key="taskId">
                <task @removed="removeTask" @updateTask="updateTask" :taskItem="taskItem" :taskId="taskId"></task>
            </div>
        </div>
    </div>
</template>

<style scoped>
#box{
    display: grid;
    gap: 2px;
    grid-template-columns: repeat(12, 1fr);
    grid-template-rows: repeat(12, 1fr); 
    background-color: rgba(31, 30, 37, 0.851);
    width: 100%;
    height: 100%;
    border-radius: 10px;
    color: rgb(220, 219, 219);
    font-weight: normal;
    cursor: default;
}

/* Visualize grid boxes */
#box > div{
    background-color: rgba(72, 72, 95, 0.225);
    border-radius: 5px;
    box-shadow: 0px 0px 5px rgb(72, 72, 95);
    margin: 10px;
}

#add{
    grid-area: 1 / 11 / 2 / 13;
    font-size: 30px;
    padding: 10px;
    text-align: center;
    cursor: pointer;
}

#title{
    grid-area: 1 / 1 / 2 / 11;
    font-size: 30px;
    padding: 10px;
}

#tasks{
    grid-area: 2 / 1 / 13 / 13;
}

@media (min-width: 1024px){
    #box{
        width: 50vw;
        height: 85vh;
    }
    
    #add{
        grid-area: 1 / 12 / 2 / 13;
    }

    #title{
        grid-area: 1 / 1 / 2 / 12;
    }
}
</style>