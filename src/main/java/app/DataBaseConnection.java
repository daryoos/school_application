package app;

import my_sql_tables.User;

import java.sql.*;

public class DataBaseConnection {
    public Connection init(User user) {
        String url = "jdbc:mysql://localhost:3306/proiect";
        String password = null;

        switch(user.getType()) {
            case "root": {
                password = "root@123";
                break;
            }
            case "admin": {
                password = "admin";
                break;
            }
            case "student": {
                password = "student";
                break;
            }
            case "profesor": {
                password = "profesor";
                break;
            }
        }
        try {
            return DriverManager.getConnection(url, user.getType(), password);
        } catch (SQLException e) {
            System.out.println("\nLog Eroare conexiune " + e);
            return null;
        }
    }

    public boolean verifyLogin(User user) {

        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call verifyLogin(?, ?, ?)}");

            callableStatement.setString(1, user.getEmail());
            callableStatement.setString(2, user.getPassword());
            callableStatement.setString(3, user.getType());

            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                System.out.println("login primit");

                if(resultSet.next()) {
                    user.setEmail(resultSet.getString(1));
                    user.setPassword(resultSet.getString(2));
                    user.setType(resultSet.getString(3));
                    user.setId(resultSet.getInt(4));
                    user.setNume(resultSet.getString(5));
                    user.setPrenume(resultSet.getString(6));
                    user.setCnp(resultSet.getString(7));
                    user.setIban(resultSet.getString(8));
                    user.setAddress(resultSet.getString(9));
                    user.setTelephone(resultSet.getString(10));

                    System.out.println("login corect");
                    connection.close();

                    return true;
                }
                else {
                    System.out.println("login gresit");
                    connection.close();
                    return false;
                }
            }
            else {
                System.out.println("login gol");
                connection.close();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("\nLog eroare log in " + ex);
            return false;
        }
    }

    public void updateDetails(User user, String nume, String prenume, String email, String parola, String iban, String cnp, String telefon, String adresa) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call updateDetails(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            callableStatement.setString(1, nume);
            callableStatement.setString(2, prenume);
            callableStatement.setString(3, email);
            callableStatement.setString(4, parola);
            callableStatement.setString(5, iban);
            callableStatement.setString(6, cnp);
            callableStatement.setString(7, telefon);
            callableStatement.setString(8, adresa);
            callableStatement.setInt(9, user.getId());

            callableStatement.executeUpdate();
            user.setUser(email, parola, adresa, cnp, iban, telefon, nume, prenume, user.getType());
            System.out.println("updated details");

            connection.close();

        }catch (Exception ex) {
            System.out.println("Log: eroare details " + ex);
        }
    }

    public Integer getAn(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getAn(?)}");

            callableStatement.setInt(1, user.getId());

            Integer an;
            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    an = resultSet.getInt(1);
                    return an;
                }
            }
            connection.close();

            return null;
        }catch (Exception ex) {
            System.out.println("getAn " + ex);
            return null;
        }
    }

    public Integer getNrOre(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getNrOre(?)}");

            callableStatement.setInt(1, user.getId());

            Integer nrOre;
            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    nrOre = resultSet.getInt(1);
                    return nrOre;
                }
            }
            connection.close();

            return null;
        }catch (Exception ex) {
            System.out.println("getAn " + ex);
            return null;
        }
    }

    public void updateAvansat(User user, Integer an, Integer nr_ore) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call updateAvansat(?, ?, ?)}");

            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, an);
            callableStatement.setInt(3, nr_ore);

            callableStatement.executeUpdate();
            System.out.println("updated details");

            connection.close();
        }catch (Exception ex) {
            System.out.println("Log: eroare avansat " + ex);
        }
    }

    public void register(User user, String nume, String prenume, String email, String parola, String iban, String cnp, String telefon, String adresa, Integer an, Integer nr_ore) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call register(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            callableStatement.setString(1, email);
            callableStatement.setString(2, parola);
            callableStatement.setString(3, nume);
            callableStatement.setString(4, prenume);
            callableStatement.setString(5, iban);
            callableStatement.setString(6, adresa);
            callableStatement.setString(7, telefon);
            callableStatement.setString(8, cnp);
            callableStatement.setInt(9, an);
            callableStatement.setInt(10, nr_ore);

            callableStatement.executeUpdate();
            user.setUser(email, parola, adresa, cnp, iban, telefon, nume, prenume, "student");

            /*String insert = "insert into utilizator  (`cnp`, `nume`, `prenume`, `iban`, `adresa`, `nr_telefon`, `email`, `parola`, `tip`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.setString(4, iban);
            preparedStatement.setString(5, adresa);
            preparedStatement.setString(6, telefon);
            preparedStatement.setString(7, email);
            preparedStatement.setString(8, parola);
            preparedStatement.setString(9, "student");

            preparedStatement.execute();

            user.setUser(email, parola, adresa, cnp, iban, telefon, nume, prenume, "student");*/
            connection.close();

        }catch (Exception ex) {
            System.out.println("Log: eroare register:" + ex);
        }
    }

    public String[] cursuriInscrise(User user) {
        String[] cursuri = null;
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call cursuriInscrise(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();

                cursuri = new String[101];
                Integer nrCurs = 1;

                while(resultSet.next()) {
                    cursuri[nrCurs++] = resultSet.getString(1);
                    //System.out.println(cursuri[i-1]);
                }
                cursuri[0] = nrCurs.toString();
            }
            connection.close();
            return cursuri;
        } catch (Exception ex) {
            System.out.println("Log cursuri inscrise:" + ex);
            return null;
        }
    }

    public String[] cursuriNeinscrise(User user) {
        String[] cursuri = null;
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call cursuriNeinscrise(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();

                cursuri = new String[101];
                Integer i = 1;

                while(resultSet.next()) {
                    cursuri[i++] = resultSet.getString(1);
                    //System.out.println(cursuri[i-1]);
                }
                cursuri[0] = i.toString();
            }

            connection.close();
            return cursuri;
        } catch (Exception ex) {
            System.out.println("Log cursuri inscrise:" + ex);
            return null;
        }
    }

    public void inscriereCurs(User user, String curs) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call inscriereCurs(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, curs);

            callableStatement.executeUpdate();

            connection.close();

        }catch (Exception ex) {
            System.out.println("eroare inscriere: " + ex);
        }
    }

    public void iesireCurs(User user, String curs) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call iesireCurs(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setString(2, curs);

            callableStatement.executeUpdate();

            connection.close();
        }catch (Exception ex) {
            System.out.println("eroare iesire: " + ex);
        }
    }

    public void createGrup(User user, Integer cursId, String activitate, String nume) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call createGrup(?, ?, ?, ?)}");

            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, cursId);
            callableStatement.setString(3, activitate);
            callableStatement.setString(4, nume);

            callableStatement.execute();

            connection.close();
        }catch (Exception ex) {
            System.out.println("eroare create grup: " + ex);
        }
    }

    public Integer getGrupId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupId()}");

            Integer id = null;
            boolean hadResults = callableStatement.execute();

            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }

            connection.close();
            return id;
        }catch (Exception ex) {
            System.out.println("grup id " + ex);
            return null;
        }
    }

    public void grupAddStudent(User user, Integer idGrup, Integer idStudent) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call grupAddStudent(?, ?)}");

            callableStatement.setInt(1, idGrup);
            callableStatement.setInt(2, idStudent);

            callableStatement.execute();

            connection.close();
        }catch (Exception ex) {
            System.out.println("eroare add student " + ex);
        }
    }

    public void grupAddProfesor(User user, Integer idGrup, Integer idProfesor) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call grupAddProfesor(?, ?)}");

            callableStatement.setInt(1, idGrup);
            callableStatement.setInt(2, idProfesor);

            callableStatement.execute();

            connection.close();
        }catch (Exception ex) {
            System.out.println("eroare add student " + ex);
        }
    }

    public Integer[] getStudentsId(User user, Integer id_activitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsId(?)}");
            callableStatement.setInt(1, id_activitate);

            boolean hadResults = callableStatement.execute();
            Integer[] studentId;

            if(hadResults) {
                studentId = new Integer[101];
                Integer nrStudents = 1;

                ResultSet resultSet = callableStatement.getResultSet();

                while(resultSet.next()) {
                    studentId[nrStudents++] = resultSet.getInt(1);
                }
                studentId[0] = nrStudents;
                connection.close();
                return studentId;
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get students " + ex);
            return null;
        }
    }

    public String[] getStudentsEmail(User user, Integer id_activitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsEmail(?)}");
            callableStatement.setInt(1, id_activitate);

            boolean hadResults = callableStatement.execute();
            String[] studentEmail;

            if(hadResults) {
                studentEmail = new String[101];
                Integer nrStudents = 1;

                ResultSet resultSet = callableStatement.getResultSet();

                while(resultSet.next()) {
                    studentEmail[nrStudents++] = resultSet.getString(1);
                }
                studentEmail[0] = nrStudents.toString();
                connection.close();
                return studentEmail;
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get students " + ex);
            return null;
        }
    }

    public Integer[] getProfesorId(User user, Integer id_activitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProfesorId(?)}");
            callableStatement.setInt(1, id_activitate);

            boolean hadResults = callableStatement.execute();
            Integer[] profesorId;

            if(hadResults) {
                profesorId = new Integer[101];
                Integer nrProfesor = 1;

                ResultSet resultSet = callableStatement.getResultSet();

                while(resultSet.next()) {
                    profesorId[nrProfesor++] = resultSet.getInt(1);
                }
                profesorId[0] = nrProfesor;
                connection.close();
                return profesorId;
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get students " + ex);
            return null;
        }
    }

    public String[] getProfesorEmail(User user, Integer id_activitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProfesorEmail(?)}");
            callableStatement.setInt(1, id_activitate);

            boolean hadResults = callableStatement.execute();
            String[] profesorEmail;

            if(hadResults) {
                profesorEmail = new String[101];
                Integer nrProfesor = 1;

                ResultSet resultSet = callableStatement.getResultSet();

                while(resultSet.next()) {
                    profesorEmail[nrProfesor++] = resultSet.getString(1);
                }
                profesorEmail[0] = nrProfesor.toString();
                connection.close();
                return profesorEmail;
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get students " + ex);
            return null;
        }
    }

    public Integer[] getCursId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getCursId()}");

            boolean hadResults = callableStatement.execute();
            Integer[] cursId;
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();

                cursId = new Integer[101];
                Integer nrCurs = 1;

                while(resultSet.next()) {
                    cursId[nrCurs++] = resultSet.getInt(1);
                }
                cursId[0] = nrCurs;
                connection.close();
                return cursId;
            }
            return null;

        }catch (Exception ex) {
            System.out.println("get curs id" + ex);
            return null;
        }
    }

    public String[] getCursNume(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getCursNume()}");

            boolean hadResults = callableStatement.execute();
            String[] cursNume;
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();

                cursNume = new String[101];
                Integer nrCurs = 1;

                while(resultSet.next()) {
                    cursNume[nrCurs++] = resultSet.getString(1);
                }
                cursNume[0] = nrCurs.toString();
                connection.close();
                return cursNume;
            }
            return null;

        }catch (Exception ex) {
            System.out.println("get curs nume" + ex);
            return null;
        }
    }

    public Integer[] getGrupsId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupsId(?)}");
            callableStatement.setInt(1, user.getId());

            Integer[] grupsId;
            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                grupsId = new Integer[101];
                Integer nrGrups = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    grupsId[nrGrups++] = resultSet.getInt(1);
                }
                grupsId[0] = nrGrups;
                connection.close();
                return grupsId;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get groups id: " + ex);
            return null;
        }
    }

    public String[] getGrupsNume(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupsNume(?)}");
            callableStatement.setInt(1, user.getId());

            String[] grupsNume;
            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                grupsNume = new String[101];
                Integer nrGrups = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    grupsNume[nrGrups++] = resultSet.getString(1);
                }
                grupsNume[0] = nrGrups.toString();
                connection.close();
                return grupsNume;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get groups id: " + ex);
            return null;
        }
    }

    public String getGrupNume(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupNume(?)}");
            callableStatement.setInt(1, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("grup nume " + ex);
            return null;
        }
    }

    public String getGrupProfesorEmail(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupProfesorEmail(?)}");
            callableStatement.setInt(1, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
            connection.close();
            return null;

        }catch (Exception ex) {
            System.out.println("get grup prof id " + ex);
            return null;
        }
    }

    public String[] getGrupStudentEmail(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupStudentEmail(?)}");
            callableStatement.setInt(1, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] studentEmail = new String[101];
                Integer nrStudent = 1;

                ResultSet resultSet = callableStatement.getResultSet();

                while(resultSet.next()) {
                    studentEmail[nrStudent++] = resultSet.getString(1);
                }
                studentEmail[0] = nrStudent.toString();
                connection.close();
                return studentEmail;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get grup student email " + ex);
            return null;
        }
    }

    public Integer getRecentActivity(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getRecentActivity()}");

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            return null;

        }catch (Exception ex) {
            System.out.println("get recent activity " + ex);
            return null;
        }
    }

    public Integer createActivity(User user, Integer grupId, Integer nrParticipanti, String nume) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call createActivity(?, ?, ?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, grupId);
            callableStatement.setInt(3, nrParticipanti);
            callableStatement.setString(4, nume);

            callableStatement.execute();

            return getRecentActivity(user);
        }catch (Exception ex) {
            System.out.println("create activity: " + ex);
            return null;
        }
    }

    public void addStudentActivity(User user, Integer activitateId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call addStudentActivity(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, activitateId);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("add student activity: " + ex);
        }
    }

    public Integer[] getGrupActivitiesId(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupActivitiesId(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] activitiesId = new Integer[101];
                Integer nrActivities = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    activitiesId[nrActivities++] = resultSet.getInt(1);
                }
                activitiesId[0] = nrActivities;
                connection.close();
                return activitiesId;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get grup activities id: " + ex);
            return null;
        }
    }

    public String[] getGrupActivitiesNume(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupActivitiesNume(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] activitiesNume = new String[101];
                Integer nrActivities = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    activitiesNume[nrActivities++] = resultSet.getString(1);
                }
                activitiesNume[0] = nrActivities.toString();
                connection.close();
                return activitiesNume;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get grup activities nume: " + ex);
            return null;
        }
    }

    public Integer[] getActivitiesId(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivitiesId(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] activitiesId = new Integer[101];
                Integer nrActivities = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    activitiesId[nrActivities++] = resultSet.getInt(1);
                }
                activitiesId[0] = nrActivities;
                connection.close();
                return activitiesId;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get activities id: " + ex);
            return null;
        }
    }

    public String[] getActivitiesNume(User user, Integer grupId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivitiesNume(?, ?)}");
            callableStatement.setInt(1, user.getId());
            callableStatement.setInt(2, grupId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] activitiesNume = new String[101];
                Integer nrActivities = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    activitiesNume[nrActivities++] = resultSet.getString(1);
                }
                activitiesNume[0] = nrActivities.toString();
                connection.close();
                return activitiesNume;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get grup activities nume: " + ex);
            return null;
        }
    }

    public String getActivityNume(User user, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivityNume(?)}");
            callableStatement.setInt(1, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
            return null;

        }catch (Exception ex) {
            System.out.println("getActivityNume" + ex);
            return null;
        }
    }

    public String[] getActivityParticipanti(User user, Integer activitateId) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivityParticipanti(?)}");
            callableStatement.setInt(1, activitateId);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] participantiEmail = new String[101];
                Integer nrParticipanti = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    participantiEmail[nrParticipanti++] = resultSet.getString(1);
                }
                participantiEmail[0] = nrParticipanti.toString();
                connection.close();
                return participantiEmail;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getActivityParticipanti " + ex);
            return null;
        }
    }

    public Integer getStudentId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentId(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get student id " + ex);
            return null;
        }
    }

    public String[] getNoteActivitate(User user, Integer idStudent, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getNoteActivitate(?, ?)}");
            callableStatement.setInt(1, idStudent);
            callableStatement.setInt(2, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] noteActivitate = new String[101];
                Integer nrNote = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    noteActivitate[nrNote++] = resultSet.getString(1);
                }
                noteActivitate[0] = nrNote.toString();
                connection.close();
                return noteActivitate;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get note activitate " + ex);
            return null;
        }
    }

    public Integer getActivitateId(User user, String curs, String tip) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivitateId(?, ?)}");
            callableStatement.setString(1, curs);
            callableStatement.setString(2, tip);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get activitate id " + ex);
            return null;
        }
    }

    public String[] getMesaj(User user, Integer idActivity) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getMesaj(?)}");
            callableStatement.setInt(1, idActivity);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] mesaje = new String[101];
                Integer nrMesaje = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    mesaje[nrMesaje++] = resultSet.getString(1);
                }
                mesaje[0] = nrMesaje.toString();
                connection.close();
                return mesaje;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get mesaj " + ex);
            return null;
        }
    }

    public void createMesaj(User user, Integer idActivity, String text) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call createMesaj(?, ?, ?)}");
            callableStatement.setInt(1, getStudentId(user));
            callableStatement.setInt(2, idActivity);
            callableStatement.setString(3, text);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("create mesaj " + ex);
        }
    }

    public void iesireGrup(User user, Integer idActivity) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call iesireGrup(?, ?)}");
            callableStatement.setInt(1, getStudentId(user));
            callableStatement.setInt(2, idActivity);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("create mesaj " + ex);
        }
    }

    public Integer getProfesorUtilizatorId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProfesorUtilizatorId(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get profesor utilizator id " + ex);
            return null;
        }
    }

    public String[] getProfesorCursNume(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProfesorCursNume(?)}");
            callableStatement.setInt(1, getProfesorUtilizatorId(user));

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] curs = new String[101];
                Integer nrCurs = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    curs[nrCurs++] = resultSet.getString(1);
                }
                curs[0] = nrCurs.toString();
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get profesor curs nume " + ex);
            return null;
        }
    }

    public Integer[] getProfesorCursId(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProfesorCursId(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] curs = new Integer[101];
                Integer nrCurs = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    curs[nrCurs++] = resultSet.getInt(1);
                }
                curs[0] = nrCurs;
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get profesor curs id: " + ex);
            return null;
        }
    }

    public Integer[] getStudentsNotInCursId(User user, Integer idGrup) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsNotInCursId(?)}");
            callableStatement.setInt(1, idGrup);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] curs = new Integer[101];
                Integer nrCurs = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    curs[nrCurs++] = resultSet.getInt(1);
                }
                curs[0] = nrCurs;
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get students not in grup id: " + ex);
            return null;
        }
    }

    public String[] getStudentsNotInCursEmail(User user, Integer idGrup) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsNotInCursEmail(?)}");
            callableStatement.setInt(1, idGrup);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] curs = new String[101];
                Integer nrCurs = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    curs[nrCurs++] = resultSet.getString(1);
                }
                curs[0] = nrCurs.toString();
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getStudentsNotInCursEmail" + ex);
            return null;
        }
    }

    public Integer getIdCurs(User user, String nume) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getIdCurs(?)}");
            callableStatement.setString(1, nume);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getIdCurs " + ex);
            return null;
        }
    }

    public void addStudentCurs(User user, Integer idStudent, Integer idCurs) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call addStudentCurs(?, ?, ?)}");
            callableStatement.setInt(1, idCurs);
            callableStatement.setInt(2, idStudent);
            callableStatement.setInt(3, getProfesorUtilizatorId(user));

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("addStudentCurs " + ex);
        }
    }

    public String[] getStudentsInActivitateEmail(User user, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsInActivitateEmail(?)}");
            callableStatement.setInt(1, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] curs = new String[101];
                Integer nrCurs = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    curs[nrCurs++] = resultSet.getString(1);
                }
                curs[0] = nrCurs.toString();
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getStudentsInActivitateEmail" + ex);
            return null;
        }
    }

    public Integer[] getStudentsInActivitateId(User user, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsInActivitateId(?)}");
            callableStatement.setInt(1, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] curs = new Integer[101];
                Integer nrCurs = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    curs[nrCurs++] = resultSet.getInt(1);
                }
                curs[0] = nrCurs;
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("get profesor curs id: " + ex);
            return null;
        }
    }

    public void updateProcentaj(User user, Integer idActivitate, Integer procent) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call updateProcentaj(?, ?)}");
            callableStatement.setInt(1, idActivitate);
            callableStatement.setInt(2, procent);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("updateProcentaj " + ex);
        }
    }

    public void addNotaStudent(User user, Integer idActivitate, Integer idStudent, Integer nota) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call addNotaStudent(?, ?, ?)}");
            callableStatement.setInt(1, idActivitate);
            callableStatement.setInt(2, idStudent);
            callableStatement.setInt(3, nota);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("addNotaStudent " + ex);
        }
    }

    public Integer getProcentaj(User user, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getProcentaj(?)}");
            callableStatement.setInt(1, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getProcentaj " + ex);
            return null;
        }
    }

    public Integer getActivitateId1(User user, Integer idCurs, String tip) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getActivitateId1(?, ?)}");
            callableStatement.setInt(1, idCurs);
            callableStatement.setString(2, tip);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                ResultSet resultSet = callableStatement.getResultSet();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getActivitateId1 " + ex);
            return null;
        }
    }

    public Integer[] getStudentsFromCursId(User user, Integer idCurs, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsFromCursId(?, ?)}");
            callableStatement.setInt(1, idCurs);
            callableStatement.setInt(2, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] curs = new Integer[101];
                Integer nrCurs = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    curs[nrCurs++] = resultSet.getInt(1);
                }
                curs[0] = nrCurs;
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getStudentsFromCursId: " + ex);
            return null;
        }
    }

    public String[] getStudentsFromCursEmail(User user, Integer idCurs, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getStudentsFromCursEmail(?, ?)}");
            callableStatement.setInt(1, idCurs);
            callableStatement.setInt(2, idActivitate);

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] curs = new String[101];
                Integer nrCurs = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    curs[nrCurs++] = resultSet.getString(1);
                }
                curs[0] = nrCurs.toString();
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getStudentsFromCursEmail" + ex);
            return null;
        }
    }

    public void addStudentActivitate(User user, Integer idStudent, Integer idActivitate) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call addStudentActivitate(?, ?)}");
            callableStatement.setInt(1, idStudent);
            callableStatement.setInt(2, idActivitate);

            callableStatement.execute();
            connection.close();
        }catch (Exception ex) {
            System.out.println("addStudentActivitate " + ex);
        }
    }

    public Integer[] getGrupsIdProfesor(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupsIdProfesor(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                Integer[] curs = new Integer[101];
                Integer nrCurs = 1;

                ResultSet resultSet = callableStatement.getResultSet();
                while(resultSet.next()) {
                    curs[nrCurs++] = resultSet.getInt(1);
                }
                curs[0] = nrCurs;
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getGrupsIdProfesor: " + ex);
            return null;
        }
    }

    public String[] getGrupsNumeProfesor(User user) {
        try {
            Connection connection = init(user);

            CallableStatement callableStatement = connection.prepareCall("{call getGrupsNumeProfesor(?)}");
            callableStatement.setInt(1, user.getId());

            boolean hadResults = callableStatement.execute();
            if(hadResults) {
                String[] curs = new String[101];
                Integer nrCurs = 1;
                ResultSet resultSet = callableStatement.getResultSet();

                while (resultSet.next()) {
                    curs[nrCurs++] = resultSet.getString(1);
                }
                curs[0] = nrCurs.toString();
                connection.close();
                return curs;
            }
            connection.close();
            return null;
        }catch (Exception ex) {
            System.out.println("getGrupsNumeProfesor" + ex);
            return null;
        }
    }
}
