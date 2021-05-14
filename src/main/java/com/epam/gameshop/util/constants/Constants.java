package com.epam.gameshop.util.constants;


public interface Constants {

    //AddressDAO
    String ID_COLUMN = "id";
    String COUNTRY_COLUMN = "country";
    String CITY_COLUMN = "city";
    String STREET_COLUMN = "street";
    String NUMBER_COLUMN = "number_of_house";
    String INSERT_ADDRESS = "INSERT INTO address(country,city,street,number_of_house) VALUES (?,?,?,?)";
    String UNIQUE_CHECK = "SELECT id FROM address WHERE country = ? AND city = ? AND street = ? AND number_of_house = ?";
    String UPDATE_ADDRESS = "UPDATE IGNORE address SET country = ?, city = ?,street = ?, number_of_house = ? WHERE id = ?";
    String DELETE_ADDRESS = "DELETE FROM address WHERE id = ?";
    String GET_ADDRESS = "SELECT * FROM address inner JOIN user on address.id = user.address_id WHERE user.id = ?";

    //GameDAOImpl
    String GAME_ID_COLUMN = "id";
    String GAME_NAME_COLUMN = "name";
    String COAST_COLUMN = "cost";
    String RELEASE_DATE_COLUMN = "release_date";
    String DESCRIPTION_COLUMN = "description";
    String DEVELOPER_COLUMN = "developer";
    String MIN_SYS_REQ_COLUMN="min_system_req_id";
    String REC_SYS_REQ_COLUMN = "rec_system_req_id";
    String FILE_ID = "file_id";
    String INSERT_GAME = "INSERT INTO game(name,cost,release_date,description,developer,min_system_req_id," +
            "rec_system_req_id,file_id) VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_GAME = "UPDATE IGNORE game SET name = ?, cost = ?, release_date = ?, description = ?, developer = ?, file_id = ? WHERE id = ?";
    String GET_GAME = "SELECT * FROM game WHERE id = ?";
    String GET_ALL_GAMES = "SELECT * FROM game LIMIT ?,?";
    String DELETE_GAME = "DELETE FROM game WHERE  id = ?";
    String GET_USER_GAMES = "SELECT game.* FROM user_game INNER JOIN game ON user_game.game_id = game.id WHERE user_game.user_id = ?";
    String DELETE_LINKS_1 = "DELETE FROM user_game WHERE game_id = ?";
    String DELETE_LINKS_2 = "DELETE FROM comment WHERE game_id = ?";
    String DELETE_USER_GAME = "DELETE FROM user_game WHERE id = ? AND game_id = ?";
    String GAME_TO_USER = "INSERT INTO user_game(user_id,game_id) VALUES (?,?)";
    String DELETE_ALL_USER_GAMES = "DELETE FROM user_game WHERE user_id = ?";
    String COUNT_OF_RECORDS_IN_GAME_TABLE = "SELECT COUNT(*) AS count FROM game";
    String COUNT_OF_FOUNDED_RECORDS_IN_GAME_TABLE = "SELECT COUNT(*) AS count FROM game WHERE name LIKE ?";
    String COUNT_OF_RECORDS_IN_USER_TABLE = "SELECT COUNT(*) AS count FROM user";
    String COUNT = "count";
    String SEARCH_GAME = "SELECT * FROM game WHERE name LIKE ? LIMIT ?,?";
    String SEARCH_GAME_NAME = "searchGame";

    //SystemRequirementsDAO
    String ID_SYS = "id";
    String OPERATION_SYS = "operation_system";
    String CPU_NAME_SYS = "cpu_name";
    String CPU_FREQ_SYS = "cpu_frequency";
    String RAM_SYS = "ram";
    String ADAPTER_NAME_SYS = "video_adapter_name";
    String ADAPTER_MEM_SYS = "video_adapter_memory";
    String FREE_SPACE_SYS = "free_space";
    String INSERT_SYS = "INSERT INTO system_requirements(operation_system, cpu_name, cpu_frequency," +
            " ram, video_adapter_name, video_adapter_memory, free_space) VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SYS = "UPDATE system_requirements SET operation_system = ?, cpu_name = ?, cpu_frequency = ?, ram = ?, " +
            "video_adapter_name = ?, video_adapter_memory = ?, free_space = ? WHERE id = ?";
    String DELETE_SYS = "DELETE FROM system_requirements WHERE id = ?";
    String GET_SYS = "SELECT * FROM system_requirements WHERE id = ?";


    //UserDAOImpl
    String USERID_COLUMN = "id";
    String USER_LOGIN_COLUMN = "user_login";
    String USERPASSWORD_COLUMN = "user_password";
    String DATE_OF_BIRTHDAY_COLUMN = "date_of_birthday";
    String EMAIL_COLUMN = "email";
    String NAME_COLUMN = "name";
    String SNAME_COLUMN = "sname";
    String ADMIN_COLUMN = "is_admin";
    String ADDRESS_ID = "address_id";
    String INSERT_USER = "INSERT INTO user(user_login,user_password, date_of_birthday,email,name,sname,address_id)" +
            " VALUES (?,?,?,?,?,?,?)";
    String SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    String SELECT_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE user_login = ? AND user_password = ?";
    String UPADTE_USER ="UPDATE IGNORE user SET name =? ,sname = ?, user_password = ?, date_of_birthday = ?," +
            "email = ? WHERE id = ?";
    String GET_ALL = "SELECT * FROM user WHERE id != ?";
    String DONATE = "UPDATE user SET money = money + ? WHERE id = ?";
    String MONEY_COLUMN = "money";
    String BUY_GAME = "UPDATE user SET money = money - (SELECT game.cost FROM game WHERE id = ?) WHERE id = ?";
    String UPDATE_ADMIN_ROLE = "UPDATE user SET is_admin = 1 WHERE id = ?";

    //UserFriendDAOImpl
    String SELECT_ALL_AVAILABLE_FRIENDS = "SELECT id, name, sname, email, date_of_birthday FROM user WHERE id NOT IN (SELECT user_id FROM user_user WHERE user_user.user_friend_id = ?)" +
            "AND id NOT IN (SELECT user_friend_id FROM user_user WHERE user_user.user_id = ?)" +
            "AND id != ?";
    String ADD_FRIEND = "INSERT INTO user_user(user_id, user_friend_id) VALUES (?,?)";
    String CHECK_REL = "SELECT status FROM user_user WHERE (user_id = ? AND user_friend_id = ?) OR (user_id = ? AND user_friend_id = ?)";
    String GET_ALL_USER_FRIENDS = "SELECT user.id,date_of_birthday,email,user.name,sname " +
            "FROM user_user INNER JOIN user ON (user_user.user_friend_id = user.id OR user_user.user_id = user.id) " +
            "WHERE (user_user.user_id = ? OR user_friend_id = ?) AND  user_user.status = 1 AND user.id !=?";
    String GET_ALL_USER_FRIEND_REQUESTS = "SELECT user.id,date_of_birthday,email,user.name,sname " +
            "FROM user_user INNER JOIN user ON (user_user.user_id = user.id) " +
            "WHERE (user_friend_id = ?) AND  user_user.status = 0 AND user.id !=?";;
    String DELETE_FRIEND = "DELETE FROM user_user WHERE (user_id = ? AND user_friend_id = ?) OR (user_id= ? AND user_friend_id = ?)";
    String CONFIRM_FRIEND = "UPDATE user_user SET status = 1 WHERE (user_id = ? AND user_friend_id = ?)";
    String DELETE_ALL_FRIENDS = "DELETE FROM user_user WHERE user_id = ? OR user_friend_id  = ?";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE user.id = ?";


    //ActionConstants
    String USER_ATTRIBUTE = "user";
    String USERS_ATTRIBUTE = "users";
    String DELETED_USER = "deletingUser";
    String DELETED_ADDRESS = "deletingAddress";
    String GAMES_ATTRIBUTE = "games";
    String COUNT_OF_PAGES_ATTRIBUTE = "countOfPages";
    String ADDED_GAME = "addedGame";
    String NEW_ADMIN_ID = "newAdminId";
    String OPERATION_STATUS = "operationStatus";
    String LANGUAGE = "lan";
    String CART_ATTRIBUTE = "cart";
    String DELETE_FROM_CART_GAME_ID = "deleteCart";
    String COUNT_OF_RECORDS = "count_of_records";
    String GET_USER_ACTION = "/getUser";
    String UPDATE_USER_ACTION = "/updateUser";
    String MAIN_PAGE_ACTION = "/mainPage";
    String LOGIN_JSP = "/WEB-INF/pages/Login.jsp";
    String CURRENT_PAGE = "currentPage";
    String ALL_USERS_PAGE_JSP = "/WEB-INF/pages/AllUsers.jsp";
    String DELETED_GAME = "deletedGame";
    String SHOW_GAMES_JSP = "/WEB-INF/pages/ShowGames.jsp";
    String USERS_DIR = "/users";
    String DELETE_USER_ACTION = "/deleteUser";
    String REGISTER_NEW_USER_ACTION = "/registerUser";
    String DELETE_GAME_FROM_DATABASE_ACTION = "/deleteGame";
    String MY_GAMES_ACTION = "/mainPage/myGames";
    String ADD_GAME_PAGE_JSP = "/WEB-INF/pages/AddNewGame.jsp";
    String ADD_POSTER_JSP = "/WEB-INF/pages/AddNewGamePoster.jsp";
    String ADD_POSTER_ACTION = "/addGameWithPoster";
    String ADD_GAME_ACTION = "/addGame";
    String ADD_GAME_TO_USER_ACTION = "/addGameToUser";
    String REGISTER_PAGE_JSP = "/WEB-INF/pages/RegisterPage.jsp";
    String SHOW_MY_FRIENDS_ACTION = "/mainPage/myFriends/friends";
    String SHOW_MY_REQUESTS_ACTION = "/mainPage/myFriends/requests";
    String SHOW_ALL_FRIEND_FOR_ADD = "/mainPage/myFriends/newFriendsList";
    String DELETE_FRIEND_ACTION = "/deleteFriend";
    String USER_FRIENDS_JSP = "/WEB-INF/pages/UserFriends.jsp";
    String USER_AVAILABLE_FRIENDS_JSP = "/WEB-INF/pages/UserAvailableFriends.jsp";
    String PROFILE_JSP = "/WEB-INF/pages/Profile.jsp";
    String DONATE_JSP = "/WEB-INF/pages/Donate.jsp";
    String CART_JSP = "/WEB-INF/pages/Cart.jsp";
    String SHOW_SEARCH_GAME_JSP = "/WEB-INF/pages/ShowSearchGame.jsp";
    String SHOW_GAME_INFO_JSP = "/WEB-INF/pages/GameDetailsPage.jsp";
    String FRIEND_ID = "friendID";
    String STATUS = "status";
    String ADD_FRIEND_ID = "addFriendID";
    String CONFIRM_FRIEND_ACTION = "/confirmFriend";
    String ADD_FRIEND_ACTION = "/addFriend";
    String LANGUAGE_ACTION = "/lan";
    String DONATE_ACTION = "/donate";
    String SHOW_MY_GAMES_PAGE_JSP = "/WEB-INF/pages/ShowMyGames.jsp";
    String LOGOUT_ACTION = "/logout";
    String SET_ADMIN_ACTION = "/setAdmin";
    String SHOW_REGISTER_PAGE = "/register";
    String SHOW_LOGIN_PAGE = "/login";
    String SHOW_ADD_NEW_GAME_PAGE = "/addNewGame";
    String SHOW_PROFILE_PAGE = "/profile";
    String SHOW_DONATE_PAGE = "/donatePage";
    String SEARCH_GAME_ACTION = "/searchGame";
    String ADD_GAME_TO_CART = "/addGameToCart";
    String SHOW_CART_PAGE = "/cart";
    String DELETE_FROM_CART = "/deleteFromCart";
    String BUY_FROM_CART_ACTION = "/buyGames";
    String FIRST_PAGE = "/";
    String ADD_NEW_COMMENT = "/addNewComment";

    //OperationStatus
    String OPERATION_SUCCESS = "Success";
    String OPERATION_ERROR = "Error";
    String OPERATION_WRONG_ID = "Unexpected friend id";
    String NOT_ENOUGH_MONEY = "Not enough money";
    String VALIDATION_ERROR = "Validation error";
    String GAME_AVAILABLE = "The game is already available or in the cart";
    String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password";


    //Creator
    String LOGIN = "login";
    String PASSWORD  = "password";
    String OPERATION_SYS_MIN = "operation_systemMin";
    String CPU_NAME_SYS_MIN = "cpu_nameMin";
    String CPU_FREQ_SYS_MIN = "cpu_frequencyMin";
    String RAM_SYS_MIN = "ramMin";
    String ADAPTER_NAME_SYS_MIN = "video_adapter_nameMin";
    String ADAPTER_MEM_SYS_MIN = "video_adapter_memoryMin";
    String FREE_SPACE_SYS_MIN = "free_spaceMin";
    String OPERATION_SYS_REC = "operation_systemRec";
    String CPU_NAME_SYS_REC = "cpu_nameRec";
    String CPU_FREQ_SYS_REC = "cpu_frequencyRec";
    String RAM_SYS_REC = "ramRec";
    String ADAPTER_NAME_SYS_REC = "video_adapter_nameRec";
    String ADAPTER_MEM_SYS_REC = "video_adapter_memoryRec";
    String FREE_SPACE_SYS_REC = "free_spaceRec";
    String DONATE_ATTRIBUTE = "donate";

    //Uploader
    String PROPERTIES_NAME = "info";
    String PASSWORD_PROP = "password";
    String URL_PROP = "url";
    String POSTER_DIR = "posters_dir";
    String POSTERS_FOLDER = "/posters/";

    //HTTPErrors
    String HTTPError = "HTTPError";
    String ERROR_500 = "500";
    String ERROR_404 = "404";
    String ERROR_JSP = "/WEB-INF/pages/ErrorPage.jsp";

    String SHOW_GAME_INFO = "/showGameInfo";
    String INSERT_FILE = "INSERT INTO file(data,mime_type) VALUES (?,?)";
    String GET_FILE_BY_ID = "SELECT * FROM file WHERE id = ?";
    String ERROR_MESSAGE = "errorMessage";
    String AVAILABLE_FRIENDS = "availableFriends";
    String SELECT_COMMENT_BY_GAME = "SELECT comment.*, user.name, user.sname FROM comment " +
            "INNER JOIN user ON comment.user_id = user.id WHERE comment.game_id = ?";
    String INSERT_INTO_COMMENT = "INSERT INTO comment(user_id,game_id,message) VALUES (?,?,?)";
    String CREATE_DATE_COLUMN = "create_date";
    String MESSAGE_COLUMN = "message";
    String DATA_COLUMN = "data";
    String MIME_TYPE_COLUMN = "mime_type";
    String GAME_INFO_PARAMETER = "gameInfo";
}


