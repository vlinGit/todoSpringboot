<script setup lang="ts">
import { defineProps, onMounted, ref } from 'vue';
import type { TaskItem } from "./taskBox.vue";

const props = defineProps<{
    taskId: string | number;
    taskItem: TaskItem;
}>();
const isChecked = ref(false);
const isDeleted = ref(false);
const emit = defineEmits(['updateTask', 'removed']);

const displayTaskName = () => {
    if (props.taskItem){
        return props.taskItem.name;
    }
    return "New task";
};

const toggleCheckbox = () => {
    isChecked.value = !isChecked.value;
    console.log(isChecked.value);
    emit('updateTask', [props.taskId, props.taskItem.name, isChecked.value]);
};

const unfocus = (event: KeyboardEvent) => {
    const elem = event.target as HTMLElement;
    elem.blur();
}

const trash = () => {
    isDeleted.value = true;
}

onMounted(() => {
    isChecked.value = props.taskItem.done;
});
</script>

<template>
    <div v-if="!isDeleted" class="task">
        <div @click="toggleCheckbox" class="checkbox" :class="{ checkedBox: isChecked }"></div>
        <p @keydown.enter.prevent="unfocus($event); $emit('updateTask', [props.taskId, ($event.target as HTMLElement).innerHTML, props.taskItem.done])" @focusout="$emit('updateTask', [props.taskId, ($event.target as HTMLElement).innerHTML, props.taskItem.done])" contenteditable="true" :class="{ checkedText: isChecked }">
            {{ displayTaskName() }}</p>
        <div @click="trash(); $emit('removed', [props.taskId])" :class="trash">X</div>
    </div>
</template>

<style scoped>
.task{
    display: inline-flex;
    width: 100%;
    height: 30px;
    gap: 10px;
    padding: 20px;
    transition: text-shadow 200ms ease-in-out;
}

.task p{
    flex-grow: 1;
    width: 100%;
    height: 20px;
    cursor: pointer;
}

.task:hover{
    text-shadow: 0 0 4px white;
}

.task p[contenteditable = "true"]:focus{
    outline: none;
    border: 0px;
}

.checkbox{
    width: 20px;
    height: 20px;
    border: 2px solid white;
    border-radius: 2px;
}

.checkedBox{
    background-color:rgb(97, 97, 105);
}

.checkedText{
    text-decoration: line-through;
    color: rgba(255, 255, 255, 0.214);
}

</style>