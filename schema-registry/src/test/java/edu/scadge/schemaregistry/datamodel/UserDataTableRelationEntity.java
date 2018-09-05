package edu.scadge.schemaregistry.datamodel;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "UserDataTableRelation" )
public class UserDataTableRelationEntity
{
  private String foreignUserDataTableId; // FK
  private String userDataTableColumnId; // PK-FK

  // links
  private UserDataTableEntity foreignUserDataTable;
  private UserDataTableColumnEntity userDataTableColumn;

  public UserDataTableRelationEntity()
  {
  }

  @Id
  public String getUserDataTableColumnId()
  {
    return userDataTableColumnId;
  }

  private void setUserDataTableColumnId( String userDataTableColumnId )
  {
    this.userDataTableColumnId = userDataTableColumnId;
  }

  @Basic
  @Column( name = "foreignUserDataTableId", nullable = false, insertable = false, updatable = false )
  public String getForeignUserDataTableId()
  {
    return foreignUserDataTableId;
  }

  private void setForeignUserDataTableId( String foreignUserDataTableId )
  {
    this.foreignUserDataTableId = foreignUserDataTableId;
  }

  @Override
  public boolean equals( Object o )
  {
    if( this == o )
      return true;
    if( o == null || getClass() != o.getClass() )
      return false;

    UserDataTableRelationEntity that = (UserDataTableRelationEntity) o;

    if( foreignUserDataTableId != null ? !foreignUserDataTableId.equals( that.foreignUserDataTableId ) : that.foreignUserDataTableId != null )
      return false;
    if( userDataTableColumnId != null ? !userDataTableColumnId.equals( that.userDataTableColumnId ) : that.userDataTableColumnId != null )
      return false;

    return true;
  }

  @Override
  public int hashCode()
  {
    int result = foreignUserDataTableId != null ? foreignUserDataTableId.hashCode() : 0;
    result = 31 * result + (userDataTableColumnId != null ? userDataTableColumnId.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn( name = "foreignUserDataTableId", referencedColumnName = "id", nullable = false )
  public UserDataTableEntity getForeignUserDataTable()
  {
    return foreignUserDataTable;
  }

  public void setForeignUserDataTable( UserDataTableEntity foreignUserDataTable )
  {
    this.setForeignUserDataTableId( foreignUserDataTable.getId() );
    this.foreignUserDataTable = foreignUserDataTable;
  }

  @OneToOne( fetch = FetchType.LAZY )
  @MapsId
  @JoinColumn( name = "userDataTableColumnId" )
  @OnDelete( action = OnDeleteAction.CASCADE )
  public UserDataTableColumnEntity getUserDataTableColumn()
  {
    return userDataTableColumn;
  }

  public void setUserDataTableColumn( UserDataTableColumnEntity userDataTableColumn )
  {
    if( userDataTableColumn == null )
    {
      this.userDataTableColumnId = null;
      this.userDataTableColumn = null;
    }
    else
    {
      this.userDataTableColumnId = userDataTableColumn.getId();
      this.userDataTableColumn = userDataTableColumn;
    }
  }
}
