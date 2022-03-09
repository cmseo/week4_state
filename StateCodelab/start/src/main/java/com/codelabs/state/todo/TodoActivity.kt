/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.codelabs.state.ui.StateCodelabTheme

class TodoActivity : AppCompatActivity() {

    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    TodoActivityScreen(
                        todoViewModel = todoViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun TodoActivityScreen(todoViewModel: TodoViewModel) {
    // 2. Pass the state down
//    val items = listOf<TodoItem>()
    val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
    // 1. Flow the events up
    TodoScreen(
        items = items,
        onAddItem =
        // todoViewModel::addItem
        //  method reference syntax
        //      https://tourspace.tistory.com/110
        //      https://medium.com/harrythegreat/%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9D%98-%EB%8D%94%EB%B8%94%EC%BD%9C%EB%A1%A0-%EC%B0%B8%EC%A1%B0-73ff25484586
        {
                    todoViewModel.addItem(
                        item = it
                    )
        },
        onRemoveItem = {
            todoViewModel.removeItem(
                item = it
            )
        })
}
/*
* In this section we explored how to build a unidirectional data flow design in Compose using ViewModels.
* We also saw how to use a stateless composable to display a stateful UI by using a technique called state hoisting. And, we continued to explore how to think about dynamic UIs in terms of state and events.
* In the next section we'll explore adding memory to composable functions.
* */