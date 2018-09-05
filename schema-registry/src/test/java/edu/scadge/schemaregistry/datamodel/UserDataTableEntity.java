package edu.scadge.schemaregistry.datamodel;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table( name = "UserDataTable" )
public class UserDataTableEntity
{
  private String id; // PK
  private String name;
  private Collection<UserDataTableColumnEntity> userDataTableColumns = new ArrayList<>();
  private Collection<UserDataTableRelationEntity> userDataTableRelations = new ArrayList<>();

  public UserDataTableEntity()
  {
  }

  @Id
  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  @Basic
  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  @Override
  public boolean equals( Object o )
  {
    if( this == o )
      return true;
    if( o == null || getClass() != o.getClass() )
      return false;

    UserDataTableEntity that = (UserDataTableEntity) o;

    if( id != null ? !id.equals( that.id ) : that.id != null )
      return false;
    if( name != null ? !name.equals( that.name ) : that.name != null )
      return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @OneToMany( mappedBy = "userDataTable", cascade = CascadeType.ALL, orphanRemoval = true )
  @OnDelete( action = OnDeleteAction.CASCADE )
  public Collection<UserDataTableColumnEntity> getUserDataTableColumns()
  {
    return userDataTableColumns;
  }

  public void setUserDataTableColumns( Collection<UserDataTableColumnEntity> userDataTableColumns )
  {
    this.userDataTableColumns = userDataTableColumns;
  }

  @OneToMany( mappedBy = "foreignUserDataTable", cascade = CascadeType.ALL, orphanRemoval = true )
  @OnDelete( action = OnDeleteAction.CASCADE )
  public Collection<UserDataTableRelationEntity> getUserDataTableRelations()
  {
    return userDataTableRelations;
  }

  public void setUserDataTableRelations( Collection<UserDataTableRelationEntity> userDataTableRelations )
  {
    this.userDataTableRelations = userDataTableRelations;
  }
}