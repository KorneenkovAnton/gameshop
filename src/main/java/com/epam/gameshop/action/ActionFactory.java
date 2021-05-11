package com.epam.gameshop.action;

import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory implements Constants {
    private final Map<String,Action> actions = new HashMap<>();

    public ActionFactory() {
        actions.put(FIRST_PAGE,new GetAllGamesAction());
        actions.put(GET_USER_ACTION, new GetUserAction());
        actions.put(UPDATE_USER_ACTION,new UpdateUserAction());
        actions.put(MAIN_PAGE_ACTION,new GetAllGamesAction());
        actions.put(USERS_DIR,new ShowUsersAction());
        actions.put(DELETE_USER_ACTION, new DeleteUserAction());
        actions.put(REGISTER_NEW_USER_ACTION,new RegisterUserAction());
        actions.put(DELETE_GAME_FROM_DATABASE_ACTION, new DeleteGameAction());
        actions.put(MY_GAMES_ACTION,new ShowMyGamesAction());
        actions.put(ADD_GAME_ACTION, new CreateNewGameAction());
        actions.put(ADD_POSTER_ACTION,new AddNewGameAction());
        actions.put(ADD_GAME_TO_USER_ACTION, new AddGameToUserAction());
        actions.put(SHOW_MY_FRIENDS_ACTION, new MyFriendsAction());
        actions.put(DELETE_FRIEND_ACTION,new DeleteFriendAction());
        actions.put(SHOW_MY_REQUESTS_ACTION, new ShowMyFriendRequestsAction());
        actions.put(CONFIRM_FRIEND_ACTION,new ConfirmFriendAction());
        actions.put(ADD_FRIEND_ACTION, new AddFriendAction());
        actions.put(LANGUAGE_ACTION,new LanguageAction());
        actions.put(DONATE_ACTION, new DonateAction());
        actions.put(LOGOUT_ACTION,new LogoutAction());
        actions.put(SET_ADMIN_ACTION, new SetAdminAction());
        actions.put(SHOW_REGISTER_PAGE, new ShowRegisterPageAction());
        actions.put(SHOW_LOGIN_PAGE,new ShowLoginPageAction());
        actions.put(SHOW_ADD_NEW_GAME_PAGE,new ShowAddNewGamePageAction());
        actions.put(SHOW_PROFILE_PAGE, new ShowProfilePageAction());
        actions.put(SHOW_DONATE_PAGE, new ShowDonatePageAction());
        actions.put(SEARCH_GAME_ACTION, new SearchGameAction());
        actions.put(ADD_GAME_TO_CART,new AddGameToCartAction());
        actions.put(SHOW_CART_PAGE,new ShowCartPageAction());
        actions.put(DELETE_FROM_CART,new DeleteFromCartAction());
        actions.put(BUY_FROM_CART_ACTION,new AddGamesFromCartToUserAction());
        actions.put(SHOW_ALL_FRIEND_FOR_ADD,new ShowAvailableFriendsAction());
        actions.put(SHOW_GAME_INFO, new ShowGameInfoPage());
        actions.put(ADD_NEW_COMMENT, new AddNewCommentAction());
    }

    public synchronized Action getAction(HttpServletRequest request, HttpServletResponse response){
        String actionKey = request.getServletPath();

        return actions.get(actionKey);
    }
}
