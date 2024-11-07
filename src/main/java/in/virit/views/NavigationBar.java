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
import com.vaadin.flow.router.Route;

@Route
public class NavigationBar extends HorizontalLayout {

    private final Person person = new Person("John", "Doe", "https://randomuser.me/api/portraits/med/men/75.jpg");

    public NavigationBar() {
        setSpacing(false);
        getStyle().set("background", "var(--lumo-contrast-5pct)");
        add(new AvatarMenu(person));
    }

    private static class AvatarMenu extends Button {
        public AvatarMenu(Person person) {
            super(new PersonAvatar(person));
            addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);
            getStyle().set("margin", "var(--lumo-space-s)");
            getStyle().set("margin-inline-start", "auto");
            getStyle().set("border-radius", "50%");
            getIcon().getStyle().set("cursor", "pointer");

            Popover popover = new Popover();
            popover.setTarget(this);
            popover.setModal(true);
            popover.setOverlayRole("menu");
            popover.setAriaLabel("User menu");
            popover.setPosition(PopoverPosition.BOTTOM_END);
            popover.addThemeVariants(PopoverVariant.LUMO_NO_PADDING);

            popover.add(new UserInfo(person), new MenuItems());

        }
    }

    public static class MenuItems extends VerticalLayout {
        public MenuItems() {
            setSpacing(false);
            setPadding(false);
            addClassName("userMenuLinks");

            addItem("User profile");
            addItem("Preferences");
            addItem("Sign out");

        }

        public void addItem(String text) {
            Anchor link = new Anchor("#", text);
            link.getElement().setAttribute("role", "menuitem");
            add(link);
        }
    }

    public static class UserInfo extends HorizontalLayout {
        public UserInfo(Person person) {
            addClassName("userMenuHeader");
            setSpacing(false);

            Div fullName = new Div(person.fullName());
            fullName.getStyle().set("font-weight", "bold");
            Div nickName = new Div(person.account());
            nickName.addClassName("userMenuNickname");

            add(new LargePersonAvatar(person), new Div(fullName, nickName));

        }
    }

    private static class PersonAvatar extends Avatar {
        public PersonAvatar(Person person) {
            super(person.fullName());
            setImage(person.pictureUrl());
            getStyle().set("display", "block");
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