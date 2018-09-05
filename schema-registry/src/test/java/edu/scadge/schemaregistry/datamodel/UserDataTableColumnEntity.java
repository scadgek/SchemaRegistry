package edu.scadge.schemaregistry.datamodel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "UserDataTableColumn" )
public class UserDataTableColumnEntity
{
  private String id; // PK
  private String name;
  private String userDataTableId; // FK

  // links
  private UserDataTableEntity userDataTable;

  public UserDataTableColumnEntity()
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

  @Column( name = "userDataTableId", insertable = false, updatable = false )
  public String getUserDataTableId()
  {
    return userDataTableId;
  }

  private void setUserDataTableId( String userDataTableId )
  {
    this.userDataTableId = userDataTableId;
  }

  @Override
  public boolean equals( Object o )
  {
    if( this == o )
      return true;
    if( !(o instanceof UserDataTableColumnEntity) )
      return false;

    UserDataTableColumnEntity that = (UserDataTableColumnEntity) o;

    if( id != null ? !id.equals( that.id ) : that.id != null )
      return false;
    if( name != null ? !name.equals( that.name ) : that.name != null )
      return false;
    return userDataTableId != null ? userDataTableId.equals( that.userDataTableId ) : that.userDataTableId == null;
  }

  @Override
  public int hashCode()
  {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (userDataTableId != null ? userDataTableId.hashCode() : 0);
    return result;
  }

  @ManyToOne( fetch = FetchType.LAZY )
  @JoinColumn( name = "userDataTableId" )
  public UserDataTableEntity getUserDataTable()
  {
    return userDataTable;
  }

  public void setUserDataTable( UserDataTableEntity userDataTable )
  {
    this.setUserDataTableId( userDataTable.getId() );
    this.userDataTable = userDataTable;
  }
}
