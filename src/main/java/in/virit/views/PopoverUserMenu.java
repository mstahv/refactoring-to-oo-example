package in.virit.views;

import com.vaadin.flow.component.Html;
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
public class PopoverUserMenu extends HorizontalLayout {

    private Person person = new Person("John", "Doe", "https://randomuser.me/api/portraits/med/men/75.jpg");

    public PopoverUserMenu() {
        setSpacing(false);
        getStyle().set("background", "var(--lumo-contrast-5pct)");

        Button button = new AvatarButton(person);

        Popover popover = new Popover();
        popover.setModal(true);
        popover.setOverlayRole("menu");
        popover.setAriaLabel("User menu");
        popover.setTarget(button);
        popover.setPosition(PopoverPosition.BOTTOM_END);
        popover.addThemeVariants(PopoverVariant.LUMO_NO_PADDING);

        HorizontalLayout userInfo = new UserInfo(person);

        VerticalLayout linksLayout = new VerticalLayout();
        linksLayout.setSpacing(false);
        linksLayout.setPadding(false);
        linksLayout.addClassName("userMenuLinks");

        Anchor profile = new Anchor("#", "User profile");
        profile.getElement().setAttribute("role", "menuitem");

        Anchor preferences = new Anchor("#", "Preferences");
        preferences.getElement().setAttribute("role", "menuitem");

        Anchor signOut = new Anchor("#", "Sign out");
        signOut.getElement().setAttribute("role", "menuitem");

        linksLayout.add(profile, preferences, signOut);

        popover.add(userInfo, linksLayout);

        add(button, popover);
    }

    public static class UserInfo extends HorizontalLayout {
        public UserInfo(Person person) {
            addClassName("userMenuHeader");
            setSpacing(false);

            Div fullName = new Div(person.fullName());
            fullName.getStyle().set("font-weight", "bold");
            Div nickName = new Div(person.account());
            nickName.addClassName("userMenuNickname");

            add(
                    new LargePersonAvatar(person),
                    new Div(
                        fullName,
                        nickName
                    )
            );

        }
    }

    private static class AvatarButton extends Button {
        public AvatarButton(Person person) {
            super(new PersonAvatar(person));
            addThemeVariants(ButtonVariant.LUMO_ICON,
                    ButtonVariant.LUMO_TERTIARY_INLINE);
            getStyle().set("margin", "var(--lumo-space-s)");
            getStyle().set("margin-inline-start", "auto");
            getStyle().set("border-radius", "50%");
            getIcon().getStyle().set("cursor", "pointer");
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