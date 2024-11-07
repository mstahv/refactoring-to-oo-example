package in.virit.views;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;

@Route
public class NavigationBar extends HorizontalLayout {

    private final Person person = new Person("John", "Doe", "https://randomuser.me/api/portraits/med/men/75.jpg");

    public NavigationBar() {
        add(new AvatarMenu(person));

        setSpacing(false);
        getStyle().setBackground("var(--lumo-contrast-5pct)");
    }

    private static class AvatarMenu extends Button {
        public AvatarMenu(Person person) {
            super(new PersonAvatar(person));

            Popover popover = new Popover(
                    new UserInfo(person),
                    new MenuItems()
            );
            popover.setTarget(this);
            popover.setModal(true);

            popover.setOverlayRole("menu");
            popover.setAriaLabel("User menu");

            addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);
            getStyle().setMargin("var(--lumo-space-s)");
            getStyle().setMarginInlineStart("auto");
            getStyle().setBorderRadius("50%");
            getIcon().getStyle().setCursor("pointer");

            popover.setPosition(PopoverPosition.BOTTOM_END);
            popover.addThemeVariants(PopoverVariant.LUMO_NO_PADDING);

        }
    }

    public static class MenuItems extends VerticalLayout {
        public MenuItems() {
            addItem("User profile");
            addItem("Preferences");
            addItem("Sign out");

            setSpacing(false);
            setPadding(false);
            addClassName("userMenuLinks");
        }

        public void addItem(String text) {
            Anchor link = new Anchor("#", text);
            link.getElement().setAttribute("role", "menuitem");
            add(link);
        }
    }

    public static class UserInfo extends HorizontalLayout {
        public UserInfo(Person person) {
            Div fullName = new Div(person.fullName());
            Div nickName = new Div(person.account());
            add(new LargePersonAvatar(person), new Div(fullName, nickName));

            addClassName("userMenuHeader");
            setSpacing(false);
            fullName.getStyle().setFontWeight("bold");
            nickName.addClassName("userMenuNickname");
        }
    }

    private static class PersonAvatar extends Avatar {
        public PersonAvatar(Person person) {
            super(person.fullName());
            setImage(person.pictureUrl());

            getStyle().setDisplay(Style.Display.BLOCK);
            getElement().setAttribute("tabindex", "-1");
        }
    }

    private static class LargePersonAvatar extends PersonAvatar {
        public LargePersonAvatar(Person person) {
            super(person);

            addThemeVariants(AvatarVariant.LUMO_LARGE);
        }
    }
}