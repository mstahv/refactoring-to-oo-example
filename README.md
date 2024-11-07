# Code structuring example

I have heard some smart people claiming that "declarative approach", like HTML or JSX, is better than Java for the UI because automatically formatting the code in IDE then shows the structure of UI elements. Should the solution be to change to another language (you don't necessarily master that well) or start writing better Java code that is easier to read (and for IDE to auto-format)🤔

This project is an example/test of how to structure Java code to make it easier to read and understand, using an *innovation* called classes. For some reason I, like many Java developers, have a tendency to avoid creating those unless they help in re-use or help to write less lines of code, but I'm leaning towards lowering the barrier to minimum. Even if there would be a line or two more code. It is one of the easiest ways to force to you give better names for things and to group related things together. And very easy to do.

Also, it is not necessary to always create a separate .java file. You can create inner classes (also static) if they are only used in one place. Or even use anonymous classes if they are only used in one place, and you really can't figure out a decent name for your class: you'll still get a *formatting friendly* structure instead of 100 lines of boring code starting from the same line.

The original code snippet (stolen from Vaadin docs) is in [PopoverUserMenuOriginalExample.java](https://github.com/mstahv/refactoring-to-oo-example/blob/css-as-css/src/main/java/in/virit/views/PopoverUserMenuOriginalExample.java) and a refactored version is in [NavigationBar.java](https://github.com/mstahv/refactoring-to-oo-example/blob/css-as-css/src/main/java/in/virit/views/NavigationBar.java). A related article discussing the topic to follow...

## Running the application
The project is a standard Maven project. To run it from the command line,
type `mvn`, then open http://localhost:8080 in your browser.

You can also import the project to your IDE of choice as you would with any
Maven project. Read more on [how to set up a development environment for
Vaadin projects](https://vaadin.com/docs/latest/guide/install) (Windows, Linux, macOS).

## Deploying to Production
To create a production build, call `mvn clean package -Pproduction`.
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/myapp-1.0-SNAPSHOT.jar` (NOTE, replace 
`myapp-1.0-SNAPSHOT.jar` with the name of your jar).

## Project structure

- `MainView.java` in `src/main/java` is an example Vaadin view.
- `src/main/resources` contains configuration files and static resources
- The `frontend` directory in the root folder is where client-side 
  dependencies and resource files should be placed.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Watch training videos and get certified at [vaadin.com/learn/training]( https://vaadin.com/learn/training).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- Find a collection of solutions to common use cases in [Vaadin Cookbook](https://cookbook.vaadin.com/).
- Find Add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Discord channel](https://discord.gg/MYFq5RTbBn).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin/).
