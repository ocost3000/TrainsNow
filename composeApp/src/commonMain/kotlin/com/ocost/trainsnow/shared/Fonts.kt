import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import trainsnow.composeapp.generated.resources.Res
import trainsnow.composeapp.generated.resources.texgyreheros_bold
import trainsnow.composeapp.generated.resources.texgyreheros_regular

// MTA line icon Font
// only need bold for now
@Composable
internal fun TextGyreHerosTypography(): Typography {
    val textGyreHerosFont = FontFamily(
        Font(Res.font.texgyreheros_regular, FontWeight.Normal),
        Font(Res.font.texgyreheros_bold, FontWeight.Bold),
    )
    return with(MaterialTheme.typography) {
        copy(
            titleMedium = titleMedium.copy(fontFamily = textGyreHerosFont, fontWeight = FontWeight.Bold)
        )
    }
}