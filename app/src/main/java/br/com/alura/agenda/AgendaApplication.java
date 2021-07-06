package br.com.alura.agenda;

import android.app.Application;

import androidx.room.Room;


import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private void criaAlunosTeste() {
        AgendaDatabase database = Room
                .databaseBuilder(this, AgendaDatabase.class, "agenda.db")
                .allowMainThreadQueries()
                .build();
        RoomAlunoDAO dao = database.getRoomAlunoDAO();

        dao.salva(new Aluno("Ademir", "1222333444", "ademir@gmail.com"));
        dao.salva(new Aluno("Sophia", "1222333444", "sophia@gmail.com"));
    }
}
