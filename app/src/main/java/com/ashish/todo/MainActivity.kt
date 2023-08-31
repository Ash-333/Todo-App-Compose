package com.ashish.todo

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ashish.todo.db.TodoItem
import com.ashish.todo.db.TodoViewModel
import com.ashish.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TodoTheme {
                Surface{
                    Box(
                        modifier=Modifier.padding(10.dp)
                    ){
                        FabButton()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FabButton() {
        Scaffold(
            floatingActionButton = {
                val openDialog = remember {
                    mutableStateOf(false)
                }
                FloatingActionButton(onClick = {
                    openDialog.value = true

                }) {
                    ShowAlert(openDialog = openDialog)
                    Icon(Icons.Default.Add, contentDescription = "Icon")
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                TodoList(todoViewModel = todoViewModel)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowAlert(openDialog: MutableState<Boolean>) {
        var title = remember {
            mutableStateOf("")
        }

        var description = remember {
            mutableStateOf("")
        }

        if(openDialog.value){
            AlertDialog(onDismissRequest = {
                openDialog.value = false
            },

                title = {
                    Text(text = "Add Todo")
                },

                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        OutlinedTextField(value = title.value,
                            placeholder = { Text(text = "Enter title") },
                            label = { Text(text = "Title") },
                            modifier = Modifier.padding(4.dp),
                            onValueChange = {
                                title.value = it
                            })

                        OutlinedTextField(value = description.value,
                            placeholder = { Text(text = "Enter description") },
                            label = { Text(text = "Description") },
                            modifier = Modifier.padding(4.dp),
                            onValueChange = {
                                description.value = it
                            })
                    }
                },

                confirmButton = {
                    OutlinedButton(onClick = {
                        if (!TextUtils.isEmpty(title.value) && !TextUtils.isEmpty(description.value)){
                            val newTodo = TodoItem(
                                title = title.value,
                                description = description.value,
                                isCompleted = false
                            )
                            todoViewModel.insertTodo(newTodo)
                            Toast.makeText(this@MainActivity,"Inserted",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@MainActivity,"Fill required values",Toast.LENGTH_SHORT).show()
                        }

                        openDialog.value = false
                    }) {
                        Text(text = "Save", modifier = Modifier.padding(4.dp))
                    }
                })
        }
    }

}

