package com.ashish.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ashish.todo.db.TodoItem
import com.ashish.todo.db.TodoViewModel

@Composable
fun TodoList(todoViewModel: TodoViewModel) {
    val todo = todoViewModel._todo.collectAsState()
    LazyColumn(content = {
        items(todo.value) {
            TodoItems(todo = it){
                todoViewModel.deleteTodo(it)
            }
        }
    })
}

@Composable
fun TodoItems(todo: TodoItem,onDeleteClicked: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier=Modifier.weight(1f)

            ) {
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = todo.description,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodySmall,
                    
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(
                onClick = onDeleteClicked
            ) {
                Text(text = "Done",style=MaterialTheme.typography.bodySmall)
            }
        }
    }
}
