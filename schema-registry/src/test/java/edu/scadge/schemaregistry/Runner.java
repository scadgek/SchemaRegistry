package edu.scadge.schemaregistry;

import edu.scadge.schemaregistry.datamodel.UserDataTableColumnEntity;
import edu.scadge.schemaregistry.datamodel.UserDataTableEntity;
import edu.scadge.schemaregistry.datamodel.UserDataTableRelationEntity;
import junit.framework.TestCase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner extends TestCase
{
  private EntityManagerFactory entityManagerFactory;

  @Override
  public void setUp() throws Exception
  {
    entityManagerFactory = Persistence.createEntityManagerFactory( "schema_registry" );
  }

  @Override
  public void tearDown() throws Exception
  {
    entityManagerFactory.close();
  }

  public void testBasicUsage()
  {
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    createTablesData( entityManager );

//    entityManager.clear(); // this fixes the exception

    entityManager.getTransaction().begin();
    final UserDataTableEntity table = entityManager.find( UserDataTableEntity.class, "1" );
    entityManager.remove( table );
    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();
    final UserDataTableEntity table2 = entityManager.find( UserDataTableEntity.class, "2" );
    entityManager.remove( table2 );
    entityManager.getTransaction().commit();

    entityManager.close();
  }

  private void createTablesData( EntityManager entityManager )
  {
    entityManager.getTransaction().begin();

    final UserDataTableEntity parentTable = new UserDataTableEntity();
    parentTable.setId( "1" );
    parentTable.setName( "Parent" );
    entityManager.persist( parentTable );

    final UserDataTableEntity childTable = new UserDataTableEntity();
    childTable.setId( "2" );
    childTable.setName( "Child" );
    entityManager.persist( childTable );

    final UserDataTableColumnEntity column = new UserDataTableColumnEntity();
    column.setId( "1" );
    column.setName( "child" );
    column.setUserDataTable( parentTable );
    entityManager.persist( column );

    final UserDataTableRelationEntity relation = new UserDataTableRelationEntity();
    relation.setForeignUserDataTable( parentTable );
    relation.setUserDataTableColumn( column );
    entityManager.persist( relation );

    entityManager.getTransaction().commit();
  }
}
