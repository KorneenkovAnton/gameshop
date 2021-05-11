package com.epam.gameshop.DAO;


import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.util.constants.Constants;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemRequirementsDAOImpl implements DAO<SystemRequirements>,Constants {

    @Override
    public void addToDatabase(SystemRequirements systemRequirements, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SYS,PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,systemRequirements.getOperationSystem());
        preparedStatement.setString(2,systemRequirements.getCpuName());
        preparedStatement.setDouble(3,systemRequirements.getCpuFrequency());
        preparedStatement.setInt(4,systemRequirements.getRam());
        preparedStatement.setString(5,systemRequirements.getVideoAdapterName());
        preparedStatement.setInt(6,systemRequirements.getVideoAdapterMemory());
        preparedStatement.setInt(7,systemRequirements.getFreeSpace());
        preparedStatement.executeUpdate();
        try(ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
            if(generatedKey.next()){
                systemRequirements.setId(generatedKey.getInt(1));
                closePrepareStatement(preparedStatement);
            }
            else {
                closePrepareStatement(preparedStatement);
                throw new SQLException("Creating SysReq failed");
            }
        }
    }

    @Override
    public void update(SystemRequirements systemRequirements, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SYS);
        preparedStatement.setString(1,systemRequirements.getOperationSystem());
        preparedStatement.setString(2,systemRequirements.getCpuName());
        preparedStatement.setDouble(3,systemRequirements.getCpuFrequency());
        preparedStatement.setInt(4,systemRequirements.getRam());
        preparedStatement.setString(5,systemRequirements.getVideoAdapterName());
        preparedStatement.setInt(6,systemRequirements.getVideoAdapterMemory());
        preparedStatement.setInt(7,systemRequirements.getFreeSpace());
        preparedStatement.setLong(8,systemRequirements.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }

    @Override
    public void delete(SystemRequirements systemRequirements, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SYS);
        preparedStatement.setLong(1,systemRequirements.getId());
        preparedStatement.executeUpdate();
        closePrepareStatement(preparedStatement);
    }


    @Override
    public SystemRequirements getById(long id, Connection connection) throws SQLException {
        SystemRequirements  systemRequirements = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_SYS);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            systemRequirements = getSysReq(resultSet);
        }
        closePrepareStatement(preparedStatement);
        return systemRequirements;
    }

    private SystemRequirements getSysReq(ResultSet resultSet) throws SQLException {
        SystemRequirements systemRequirementsTemp = new SystemRequirements();
        systemRequirementsTemp.setId(resultSet.getLong(ID_SYS));
        systemRequirementsTemp.setOperationSystem(resultSet.getString(OPERATION_SYS));
        systemRequirementsTemp.setCpuName(resultSet.getString(CPU_NAME_SYS));
        systemRequirementsTemp.setCpuFrequency(resultSet.getDouble(CPU_FREQ_SYS));
        systemRequirementsTemp.setRam(resultSet.getInt(RAM_SYS));
        systemRequirementsTemp.setVideoAdapterName(resultSet.getString(ADAPTER_NAME_SYS));
        systemRequirementsTemp.setVideoAdapterMemory(resultSet.getInt(ADAPTER_MEM_SYS));
        systemRequirementsTemp.setFreeSpace(resultSet.getInt(FREE_SPACE_SYS));
        return systemRequirementsTemp;
    }
}
