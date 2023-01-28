package br.com.intern.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.intern.model.Task;
import br.com.intern.utils.DBConnection;

public class TaskDAO {

	public void save(Task task) {
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO public.task(title, description, deadline, startdate, priority, responsable)	VALUES (?, ?, ?, ?, ?,?);");
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setDate(3, new Date(task.getDeadline().getTime()));
			ps.setDate(4, new Date(task.getStartDate().getTime()));
			ps.setString(5, task.getPriority().toString());
			ps.setString(6, task.getResponsable());
			ps.execute();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer idTask) {
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("delete from task where id=?");
			ps.setInt(1, idTask);
			ps.execute();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void edit(Task task) {
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE public.task\r\n"
					+ "	SET title=?, description=?, deadline=?, startdate=?, priority=?, responsable=?\r\n"
					+ "	WHERE id=?;");
			ps.setInt(7, task.getId());
			ps.setString(1, task.getTitle());
			ps.setString(2, task.getDescription());
			ps.setDate(3, new Date(task.getDeadline().getTime()));
			ps.setDate(4, new Date(task.getStartDate().getTime()));
			ps.setString(5, task.getPriority().toString());
			ps.setString(6, task.getResponsable());
			ps.executeUpdate();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void complete(Task task) {
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE public.task\r\n"
					+ "	SET status=?\r\n"
					+ "	WHERE id=?;");
			ps.setInt(2, task.getId());
			ps.setString(1,"CONCLUIDO");
			ps.executeUpdate();
			DBConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Task> search(Task task) {
		List<Task> results = new ArrayList<Task>();
		try {
			Connection connection = DBConnection.getConnection();

			PreparedStatement ps = null;

			if (task.getTitle() == "" & task.getResponsable() == "" & task.getStatus() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE id=?");
				ps.setInt(1, task.getId());
			} else if (task.getId() == null & task.getResponsable() == "" & task.getStatus() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE title LIKE ?");
				ps.setString(1, task.getTitle());
			} else if (task.getId() == null & task.getTitle() == "" & task.getStatus() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE responsable LIKE ?");
				ps.setString(1, task.getResponsable());
			} else if (task.getId() == null & task.getTitle() == "" & task.getResponsable() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE status LIKE ?");
				ps.setString(1, task.getStatus());
			}
			else if (task.getId() == null & task.getResponsable() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE title LIKE ? AND status=?");
				ps.setString(1, task.getTitle());
				ps.setString(2, task.getStatus());
			} else if (task.getTitle() == "" & task.getResponsable() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE id=? AND status=?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getStatus());
			} else if (task.getTitle() == "" & task.getId() == null) {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE responsable=? AND status=?");
				ps.setString(1, task.getResponsable());
				ps.setString(2, task.getStatus());
			} else if (task.getStatus() == "" & task.getId() == null) {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE responsable=? AND title LIKE ?");
				ps.setString(1, task.getResponsable());
				ps.setString(2, task.getTitle());
			} else if (task.getStatus() == "" & task.getResponsable() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE id=? AND title LIKE ?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getTitle());
			} else if (task.getStatus() == "" & task.getTitle() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE id=? AND responsable=?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getResponsable());
			} else if (task.getId() == null) {
				ps = connection.prepareStatement(
						"SELECT * FROM public.task WHERE title LIKE ? AND responsable=? AND status=?");
				ps.setString(1, task.getTitle());
				ps.setString(2, task.getResponsable());
				ps.setString(3, task.getStatus());
			} else if (task.getResponsable() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE title LIKE ? AND id=? AND status=?");
				ps.setString(1, task.getTitle());
				ps.setInt(2, task.getId());
				ps.setString(3, task.getStatus());
			} else if (task.getTitle() == "") {
				ps = connection.prepareStatement("SELECT * FROM public.task WHERE id=? AND responsable=? AND status=?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getResponsable());
				ps.setString(3, task.getStatus());
			} else if (task.getStatus() == "") {
				ps = connection
						.prepareStatement("SELECT * FROM public.task WHERE id=? AND responsable=? AND title LIKE ?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getResponsable());
				ps.setString(3, task.getTitle());
			}

			else if (task.getStatus() != "" & task.getTitle() != "" & task.getResponsable() != ""
					& task.getId() != null) {
				ps = connection.prepareStatement(
						"SELECT * FROM public.task WHERE id=? AND responsable=? AND title LIKE ? AND status=?");
				ps.setInt(1, task.getId());
				ps.setString(2, task.getResponsable());
				ps.setString(3, task.getTitle());
				ps.setString(4, task.getStatus());
			} else {
				list();
			}
			
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Task resultTask = new Task();
				resultTask.setId(resultSet.getInt("id"));
				resultTask.setTitle(resultSet.getString("title"));
				resultTask.setDescription(resultSet.getString("description"));
				resultTask.setDeadline(resultSet.getDate("deadline"));
				resultTask.setStartDate(resultSet.getDate("startdate"));
				resultTask.setPriority(resultSet.getString("priority"));
				resultTask.setResponsable(resultSet.getString("responsable"));
				resultTask.setStatus(resultSet.getString("status"));

				results.add(resultTask);
			}

			return results;
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Task> list() {

		try {
			Connection connection = DBConnection.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM public.task");

			ResultSet resultSet = ps.executeQuery();

			List<Task> tasks = new ArrayList<Task>();

			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setStatus(resultSet.getString("status"));
				task.setTitle(resultSet.getString("title"));
				task.setDescription(resultSet.getString("description"));
				task.setDeadline(resultSet.getDate("deadline"));
				task.setStartDate(resultSet.getDate("startdate"));
				task.setPriority(resultSet.getString("priority"));
				task.setResponsable(resultSet.getString("responsable"));

				tasks.add(task);
			}

			return tasks;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
