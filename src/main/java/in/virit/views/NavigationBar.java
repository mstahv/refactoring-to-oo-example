package in.virit.views;

import com.vaadin.flow.component.Component;
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
        add(new AvatarMenu(person));
        addClassName("navigationBar");
    }

    private static class AvatarMenu extends Button {
        public AvatarMenu(Person person) {
            super(new PersonAvatar(person));

            new MenuPopover(
                    new UserInfo(person),
                    new MenuItems()
            ).setTarget(this);

            addThemeVariants(ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_TERTIARY_INLINE);
        }

        public static class MenuPopover extends Popover {
            public MenuPopover(Component... components) {
                super(components);
                setModal(true);
                setOverlayRole("menu");
                setAriaLabel("User menu");
                setPosition(PopoverPosition.BOTTOM_END);
                addThemeVariants(PopoverVariant.LUMO_NO_PADDING);
            }

        }

        public static class MenuItems extends VerticalLayout {
            public MenuItems() {
                add(new MenuItem("User profile"));
                add(new MenuItem("Preferences"));
                add(new MenuItem("Sign out"));

                setSpacing(false);
                setPadding(false);
                addClassName("userMenuLinks");
            }


            public static class MenuItem extends Anchor {
                public MenuItem(String text) {
                    super("#", text);
                    // TODO Make a feature requests, if these roles must be set for Anchor's there should be an actual API for it
                    getElement().setAttribute("role", "menuitem");
                }
            }
        }

        public static class UserInfo extends HorizontalLayout {
            public UserInfo(Person person) {
                Div fullName = new Div(person.fullName());
                Div nickName = new Div(person.account());
                add(new LargePersonAvatar(person), new Div(fullName, nickName));

                addClassName("userMenuHeader");
                setSpacing(false);
            }
        }

    }

    private static class PersonAvatar extends Avatar {
        public PersonAvatar(Person person) {
            super(person.fullName());
            setImage(person.pictureUrl());
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