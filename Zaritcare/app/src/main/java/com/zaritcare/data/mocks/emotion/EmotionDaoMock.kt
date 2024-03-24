package com.zaritcare.data.mocks.emotion

import javax.inject.Inject

class EmotionDaoMock @Inject constructor() {
    private val smilePhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAEESURBVHgB7ZY7CsJAEIZ/I4KQiKKIoIWNlY32djb22usttPQG3kFvoeAdtLDTxkpJIYLEFxgT1iYgrjM+SDEfhCHDwH7szC4bcRd1FyHEQEgRMSoiRkXEqIgYFRGjwhfLdoDyVMV38v8TawejLk+EL2YPg1GXJxKRZw8Rnpg/P4alr4ta7FnjiaVb3qKmvi6WU7UMeGKnFZCo6eviJeC8BAeemH/iMq3X7TRM1cbdBBx4Ys4cuGyBfPe5nC9V6Kmd3Y/BgX9d+INdHKhZs0fA8dEyq+rNVRO4boB1H7gdwOHzeyzZAFLeZ1bUvzNT7WPu1PfEfoQ8e6iIGBURoyJiVEIrdgdzhz3nL/O3BAAAAABJRU5ErkJggg=="
    private val sadPhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAEFSURBVHgB7Za7DgFBFIZ/IiFxqYiGiopCTanReQTeQeEB6CQeglJCr9DazqXQbUcjOqGgWTOm2hDrnE02W5wvmZzsmUn2y/knm400x46DEBJFSBExKiJGRcSoiBgVEaPCFuvWgVXP1H/6gYl16u7q1afCFptY7urVpxKR3x4iMfikVVWrAtSK5nl/ApYHs/zAjjIZB4ZtIJ8BFltgdzT9cs5c/PsT6M2A2wMs2GKDtqmj5efLU0q631I1YeQ4sO6Yjq+U+y6l0T29l0+riAtgwRabWr9j0ntzFXG3geDESlnAvnifW9tmshxYYnoS56v3uffUNmAhH1gqIkZFxKiIGBURo/ICaqpK1+3TW5UAAAAASUVORK5CYII="
    private val upsetPhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAACCSURBVHgB7dbLDUBAFEbhSxRECShElEEByiAKoQUlEWJD4nFGIrP4v81N7mbOZiYTtFU6m4dC85TCKIVRCqMURimMcg5L8sLKZtzmm/1vYfF+cHwKuNpTzmHT0B/m054K9O2BFEZ5GxbZB+t7daerM3OlW0kpjFIYpTBKYZTCKG/DFrVMHNvHOiCiAAAAAElFTkSuQmCC"
    private val angryPhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAEYSURBVHgB7ZYxDgFREEDfio7eAejdgYRbcAcFNTpH0NHTK5TU9BxAT71MNiKydtfMhmwxL/n5m/k/2Zf5f2c2CFutkAJSoqC4mBYX0+JiWlxMi01sPIZ6PX3PaATNJlZsYsslTCbJciIVPjrd8ch/xc5n2O1gPod+n5hUpwOXC3kIzE18u309t9vZcSX2y79YvM9ZcSWB//YoKZOXbjcaz9IgX+JmE40c2I+yUoHpFGo1WK/hcIjijQb0enC7wWAA1ysW7GJSx4TZLP7yahWGw2gWOQO2OyZHJ8X1k5QgMVmTbBqrv11Mqn/aMcnaahUvwD8Vk2ydTtn79vvsnpqATUwy8U3LeWbNgBdYLS6mxcW0uJgWF9NyBxfjWdf4BCotAAAAAElFTkSuQmCC"
    private val sickPhoto: String = "iVBORw0KGgoAAAANSUhEUgAAACYAAAAiCAYAAAAzrKu4AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAFnSURBVHgB7Za/S8NAFMe/tVVMxUGlDir+qOhQf3QJOkoHBbN0CYigg6MIraOLxVGcXd0dOmdwkeJi/wEditJAdFEUf6FgsF4O/BFN0XcJJcN9IDzCJdyHe/feXWTtYKGGENKEkCLFqEgxKlKMihSjErjYZLeKncwej34IXExL6ojH2pDp1+AHYTFtWMfu7D6P3+lrH+RxpCMFP4iLJXVXDBphMeOs6IofVG5PeLQeq/BDDIIY50X+/OTQNFg6B34JU4nI+xgR4VQ6VdfLUua0Bmc/Wfcmbl6uPL/tbE3UHatHdGp5fIvyg8JEViZymBvKssmuYb+9ItWVRnZ0EXbNRvWu4hLKqwU0R1s+i+K/kFcsr26ySU5ROMrh2X76klASWGcSSiyO8kWJvy+NraJ8WfIskr8gbf7pnhnW0eexfbzhOe7IOH0tzY4j68HklUldKSGxRiKvPVSkGBUpRkWKUZFiVEIr9g66VWQY23MisAAAAABJRU5ErkJggg=="

    private val emotions = mutableListOf<EmotionMock>(
        EmotionMock(
            id = 1,
            name = "Feliz",
            image = smilePhoto
        ),
        EmotionMock(
            id = 2,
            name = "Triste",
            image = sadPhoto
        ),
        EmotionMock(
            id = 3,
            name = "Decepcionado",
            image = upsetPhoto
        ),
        EmotionMock(
            id = 4,
            name = "Enfadado",
            image = angryPhoto
        ),
        EmotionMock(
            id = 5,
            name = "Enfermo",
            image = sickPhoto
        )
    )

    fun get(): List<EmotionMock> = emotions
    fun count() = emotions.size
    fun get(name: String): EmotionMock = emotions.first { emotion -> emotion.name == name }
    fun get(id: Int): EmotionMock? = emotions.find { emotion -> emotion.id == id }
}