/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Note;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class NoteService
{
    public List<Note> getAll() throws Exception
    {
        return NoteDB.getAll();
    }
    
}
