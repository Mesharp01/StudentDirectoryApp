package com.example.studentapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.studentapp.R

@Composable
fun PopUp(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = (stringResource(id = R.string.remove_student)))
        },
        title = {
            Text(text = dialogTitle, modifier = Modifier.testTag(stringResource(R.string.dialogtitle_test_tag)))
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(R.string.confirm_popup), modifier = Modifier.testTag(
                    stringResource(R.string.confirmpopup_test_tag)
                ))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.dismiss_popup), modifier = Modifier.testTag(
                    stringResource(R.string.dismisspopup_test_tag)))
            }
        }
    )
}

