package dao;

public class User {
        private int id_utente;
        private String username;
        private String password;
        private String ruolo;


        public User(int id_utente, String username, String password, String ruolo) {
            this.id_utente = id_utente;
            this.username = username;
            this.password = password;
            this.ruolo = ruolo;
        }


        public int getid_utente() {return id_utente;}

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getRuolo() {
            return ruolo;
        }

        @Override
        public String toString() {
            return id_utente + " " + username + " " + password + " " + ruolo;
        }
    }
