package DAO;

import entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryDAO {
    public Connection conn;


    public CategoryDAO(Connection conn){
        this.conn = conn;
    }

    public Category createCategoryFromId(int id){
        Category currentCat = new Category();
        try{
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM category WHERE category_id=" + id);

            if(rs.next()){
                currentCat.setId(rs.getInt("category_id"));
                currentCat.setName(rs.getString("category_name"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return currentCat;
    }
    public void insertNewCategory(Category c){
        int id = c.getId();
        String name = c.getName();

        try{
            String sql = "INSERT OR IGNORE INTO category (category_id, category_name)" +
                    "VALUES (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Lagt til i database!");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public ArrayList<Category> findAllCategories(){
        ArrayList<Category> categories = new ArrayList<Category>();
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM category");
            while(rs.next()){
                Category currCat = new Category();
                currCat.setId(rs.getInt("category_id"));
                currCat.setName(rs.getString("category_name"));
                categories.add(currCat);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }
    public void alterCategory(Category c){
        String sql = "UPDATE category SET " +
                "category_id = ? , " +
                "category_name = ? WHERE category_id="+c.getId();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, c.getId());
            preparedStatement.setString(2, c.getName());

            preparedStatement.executeUpdate();
            System.out.println("Updated category with ID " + c.getId());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
