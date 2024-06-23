import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.backGroundColor
import theme.numberColor
import theme.operationSymbolColor
import theme.topColor

@Composable
@Preview
fun App() {
    MaterialTheme {
        KMPCalculatorApplication()
    }
}

@Composable
private fun KMPCalculatorApplication() {

    Column(modifier = Modifier.fillMaxSize()) {


        var data by remember { mutableStateOf("") }

        Box(
            modifier = Modifier.weight(3f).fillMaxWidth().background(topColor),
            contentAlignment = Alignment.Center
        ) {
            Text(text = data, fontSize = 22.sp, modifier = Modifier)
        }
        Box(modifier = Modifier.weight(7f)) {
            CalculatorKeys() { it ->
                if (it == "C") {
                    data = ""
                } else if (it == "=") {
                    data = "420"
                } else {
                    data = data + it
                }
            }
        }

    }
}

@Composable
private fun CalculatorKeys(onClick: (String) -> Unit) {



    Row(modifier = Modifier.fillMaxSize().background(backGroundColor)) {


        val numberList = getNumberList()
        LazyVerticalGrid(
            modifier = Modifier.weight(3f).fillMaxSize(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            numberList.forEachIndexed { index, item ->

                items(count = 1,
                    span = { GridItemSpan(1) }
                ) {

                    Box(
                        modifier = Modifier.weight(1f).height(130.dp).padding(5.dp)
                            .background(numberColor).clickable {
                                onClick.invoke(item)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item)
                    }
                }

            }

        }

        Column(
            Modifier.weight(1f).fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Box(
                Modifier.weight(1f).fillMaxWidth().background(operationSymbolColor)
                    .clickable { onClick("+") },
                contentAlignment = Alignment.Center
            ) {
                Text("+", color = Color.White)
            }
            Box(
                Modifier.weight(1f).fillMaxWidth().background(operationSymbolColor)
                    .clickable { onClick.invoke("-") },
                contentAlignment = Alignment.Center
            ) {
                Text("-", color = Color.White)
            }
            Box(
                Modifier.weight(1f).fillMaxWidth().background(operationSymbolColor).clickable {
                    onClick("×")
                },
                contentAlignment = Alignment.Center
            ) {
                Text("×", color = Color.White)
            }
            Box(
                Modifier.weight(1f).fillMaxWidth().background(operationSymbolColor).clickable {
                    onClick("÷")
                },
                contentAlignment = Alignment.Center
            ) {
                Text("÷", color = Color.White)
            }
        }

    }

}

private fun getNumberList(): List<String> {
    val numberList =
        mutableListOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "C", "0", "=")
    return numberList
}
