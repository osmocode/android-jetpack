package fr.uge.plutus.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField

@Composable
fun TitleAndDescriptionPage(
    navController: NavHostController,
    previousTitle: String,
    previousDescription: String,
    onTitleSubmit: (String) -> Unit,
    onDescriptionSubmit: (String) -> Unit,
) {
    val sheetVisible = remember { mutableStateOf(false) }


    val title = remember { mutableStateOf(previousTitle) }
    val description = remember { mutableStateOf(previousDescription) }

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Enter details",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Ant.spacing.default),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntTextField(
                    placeHolder = "Title",
                    onChange = { title.value = it }
                )
                AntTextField(
                    placeHolder = "Description",
                    onChange = { description.value = it }
                )
            }
            CustomButton(
                title = "Submit",
                type = CustomButtonType.PRIMARY,
                onClick = {
                    onTitleSubmit(title.value)
                    onDescriptionSubmit(description.value)
                    navController.popBackStack()
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TitleAndDescriptionPagePreview(

) {
    val navController = rememberNavController()

    TitleAndDescriptionPage(
        navController = navController,
        previousTitle = "Lorem",
        previousDescription = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        onTitleSubmit = {},
        onDescriptionSubmit = {}
    )
}