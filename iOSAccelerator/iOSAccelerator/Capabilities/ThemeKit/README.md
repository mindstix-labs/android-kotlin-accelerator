
# ThemeKit

  
  A module for using custom fonts and colors based on selected themes from the Global Design System.

  

###  Requirements
-   iOS 15+
    
-   Swift 5.6
### Description

This module allows the app to set a Theme. Based on the theme. we see colors and fonts. We just need to set the current property in the Theme enum. Custom Fonts and Colors setup is automatically handled in the module layer.

**![](https://lh7-us.googleusercontent.com/sr_bIxDm__kxcHwhYPeFQerdBR7ZdAf_nXYlO23vuWdTvyrn7wnMWc8Zat-KtXoOEAaqBlX0UCaNAUfXr7URoNukBPdfAtStqeS-2incbPXUWHEtPPMW0lI2rFh7815HKfC6fXxU9zX39ZIFSNRmdvE)**
We can refer to below Design system which has been followed:

-   Typography

![](https://lh7-us.googleusercontent.com/2DbvTdKz-wElfE_XmvznJOpaS2rcJrPJ_lM05XNLcpCLlKYuVSUcDAkcIQmUI25aw0F66dsXtub0JJ3p87PHCI5osmEQEbgAdmhDfFfayEc_R9xqIcJAADVlBLy5ZTKdczrFbMm65o5PWhT9MorGZBE)

-   Colors
### How to use ThemeKit
-   To harness the enchanting capabilities of ThemeKit within your application, ensure a seamless thematic experience by responsibly configuring "Theme.current" at the App level. This straightforward step ensures that your app gracefully adopts the chosen theme mode, providing users with a visually cohesive and delightful interface.
- To make your app look stylish with special fonts, first, tell the app about the font by registering it. Then, note down the font's name in the "Theme+FontExtension" file. Keep this file in the "Utilities" folder, specifically under the name "FontFamily.swift". This way, your fonts are organized and ready to enhance the visual charm of your app.

-  To give our app some cool colors, first, put those colors in the special file called "xcassets" managed by ThemeKit. Then, write down the color names in the "Theme+ColorExtension" file. This file is like a color menu that helps the app know which colors to use, depending on whether it's feeling dark or light. It's like telling the app to pick the right colors for the mood!
    


    

  
<p align="Right">

<h>Contributor</h>


Abdul Khan 

</p>
