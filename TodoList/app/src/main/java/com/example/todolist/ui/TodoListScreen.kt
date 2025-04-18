package com.example.todolist.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.todolist.data.database.Todo

@Composable
fun TodoListScreen(
    state: TodosState,
    actions: TodosActions,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            AddTodoField(
                onSubmit = { content -> actions.addTodo(Todo(content = content)) },
                modifier = Modifier.padding(16.dp)
            )
        }
        items(state.todos) {
            TodoItem(
                it,
                onToggle = { actions.toggleComplete(it) },
                onDelete = { actions.removeTodo(it) }
            )
        }
    }
}

@Composable
fun AddTodoField(
    onSubmit: (content: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var content by remember { mutableStateOf("") }

    OutlinedTextField(
        value = content,
        onValueChange = { content = it },
        label = { Text("TODO") },
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {
                if (content.isBlank()) return@IconButton
                onSubmit(content)
                content = ""
            }) {
                Icon(Icons.Outlined.Add, "Add TODO")
            }
        }
    )
}

@Composable
fun TodoItem(
    item: Todo,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = item.isComplete,
                onValueChange = { onToggle() },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = item.isComplete, onCheckedChange = null)
        Text(
            item.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp).weight(1F)
        )
        IconButton(onClick = onDelete) {
            Icon(Icons.Outlined.Close, "Remove TODO")
        }
    }
}
