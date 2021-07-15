package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT t.* FROM Telefone t " +
            "JOIN Aluno a " +
            "On t.alunoId = a.id " +
            "WHERE t.alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);
}
