package tw.edu.pu.csim.tcyang.basic2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.edu.pu.csim.tcyang.basic2026.ui.theme.Basic2026Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Basic2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    // 使用垂直的 Column 包起來，讓手勢區域和列表上下平分或依序排列，不再互相阻擋
    Column(modifier = modifier.fillMaxSize()) {
        // 上半部：顯示背景、文字與手勢偵測區域
        GestureHeaderSection()

        // 下半部：顯示可滑動列表
        LazyColumnExample(modifier = Modifier.weight(1f))


    }
}

@Composable
fun GestureHeaderSection() {
    var msg by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x8AE0BBE4))
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { msg = "短按" },
                    onDoubleTap = { msg = "連續兩下" },
                    onLongPress = { msg = "長按" }
                )
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "行動應用軟體開發：基本元件")
        Text(text = "作者：王奕勛")
        Text(text = "手勢結果：$msg")
    }
}

@Composable
fun LazyColumnExample(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Text(text = "標題列", modifier = Modifier.padding(8.dp)) }
        items(50) { index ->
            Text(text = "$index", modifier = Modifier.padding(8.dp))
        }
        item { Text(text = "結尾列", modifier = Modifier.padding(8.dp)) }
    }
}
