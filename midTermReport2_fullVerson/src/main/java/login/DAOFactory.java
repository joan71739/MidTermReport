package login;

public class DAOFactory {


	// DAO開方法
	private static final SQLMethod createMemberDAO() {
		SQLMethod mDAO = new SQLMethod();
		return mDAO;
	}

	// 防止取到不同DAO
	private static final SQLMethod sqlDAO = createMemberDAO();

	static SQLMethod getSQLMemberDAO() {
		return sqlDAO;
	}
}
