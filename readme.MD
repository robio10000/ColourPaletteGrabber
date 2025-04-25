
# ColourPaletteGrabber
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.6-blue.svg)
![BootstrapFX](https://img.shields.io/badge/BootstrapFX-0.4.0-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Build](https://img.shields.io/badge/Mac_build-passing_2024/04/25-brightgreen.svg)
![Build](https://img.shields.io/badge/Linux_build_(planning_for_2024/04/27)-none-red.svg)
![Build](https://img.shields.io/badge/Windows_build_(planning_for_2024/04/27)-none-red.svg)


## Description

`ColourPaletteGrabber` (short: CPG) allows you to create color palettes from pictures

### User Key Features:

- **Cross-Platform Compatibility**: Runs on Windows, macOS, and Linux. (Deployment for Linux and Windows is still in progress and coming soon!)
- **User-Friendly Interface**: Intuitive design for easy navigation and usage.
- **Responsive Design**: Adapts to different screen sizes and resolutions.
- **Drag and Drop Support**: Easily add pictures to generate palettes.
- **Copy and Paste Functionality**: Quickly copy and paste colors between palettes.
- **Color Format Support**: Support for various color formats (e.g., RGB, CMYK, HEX).

### Near Future Features (Planned):

- **More Color Format Support**: Support for additional color formats (e.g., HSL, LAB).
- **Color Palette Generator Configuration**: Allow users to configure the color palette generation process.
- **Multi-Language Support**: Available in multiple languages for a broader audience.
- **Customizable Themes**: Allows users to customize the application's appearance with different themes.

- **Color Palette Management**: Create, edit, and delete color palettes.
- **Color History**: Keep track of recently used colors for quick access.
- **Export/Import**: Save and load color palettes in various formats.
- **Version Control**: Keep track of changes to palettes over time.

### Future Features (Not Yet Planned):

- **Color Contrast Checker**: Ensure accessibility by checking color contrast ratios.
- **Color Picker**: Select colors using a color picker tool.
- **Color Harmonies**: Generate color harmonies based on selected colors.
- **Color Blindness Simulation**: Simulate how colors appear to users with various types of color blindness.
- **Search Functionality**: Quickly find colors within palettes.
- **Undo/Redo Functionality**: Keep track of changes and revert if necessary.
- **Tutorials and Documentation**: In-app tutorials and documentation for new users.
- **Keyboard Shortcuts**: Enhance productivity with keyboard shortcuts for common actions.
- **Feedback and Support**: Includes a built-in feedback system for reporting issues or requesting features.

#### Late Features:

- **Integration with Design Tools**: Export palettes to popular design software (e.g., Adobe, Sketch).
- **User Authentication**: Secure access to user-specific palettes.
- **Cloud Sync**: Sync palettes across devices using cloud storage.
- **Collaboration Features**: Share palettes with other users for collaborative projects.
- **Mobile Version**: Plans to develop a mobile version of the application for iOS and Android
- **Plugin System**: Allow third-party developers to create plugins for extended functionality.

### Screenshots:

- Screenshots will be added once the Linux and Windows builds are ready

### Technical Key Features:

- **JavaFX Support**: Utilizes JavaFX for creating an interactive user interface.
- **BootstrapFX Integration**: Modern styling for an enhanced user experience.
- **Cross-Platform**: Runs on macOS, Windows, and Linux.
- **Modular Design**: Utilizes the Java Module System (JLink and JPackage).

### Requirements:

- Java JDK 17 or higher
- Maven for the build process (or use the provided mvnw wrapper script).
  - ./mvnw ... for Mac and Linux
  - mvnw.cmd ... for Windows

---

## How to Build the Project

Follow these steps to build the project:

### 0 Note

The Linux and Windows builds are currently in planning
The project is built with Maven and uses JavaFX for the GUI.
The project is modularized, and we use JLink to create a custom runtime image.
JPackage is used to create platform-specific installers.
This means the build process is possible, but I cannot test it on Linux and Windows at the moment.
### 1 Clean the project

```bash
./mvnw clean
```

### 2 Build the project

```bash
./mvnw package
```

### 3 JLink

#### 3.1 Use this

```bash
./mvnw javafx:jlink  
```
#### 3.2 Old (Maven handles it via pom now)

```bash
./mvnw javafx:jlink -Djavafx.add-modules=javafx.controls,javafx.fxml  
```

### 4 From here, navigate to the target folder.

```bash
cd target
```

### 5 JPackage command

Requires Java JDK 14 or higher for JPackage. This project uses Java 17.

#### 5.1 Mac

```bash
jpackage --type DMG --input . --dest ./package --app-version 1.0.0 --name CPG --main-jar ./ColourPaletteGrabber-1.0-SNAPSHOT.jar --main-class dev.goerissen.colourpalettegrabber.Launcher --runtime-image ./app
```

### 6 Find the result in the package folder

```bash
ls -l ./package
```
