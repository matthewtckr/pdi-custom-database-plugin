/*! ******************************************************************************
*
* Pentaho Data Integration
*
* Copyright (C) 2002-2015 by Pentaho : http://www.pentaho.com
*
*******************************************************************************
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
******************************************************************************/

package org.pentaho.pdi.databases.custom;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.database.DatabaseInterface;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.database.MySQLDatabaseMeta;
import org.pentaho.di.core.plugins.DatabaseMetaPlugin;

@DatabaseMetaPlugin( type="MySQLFabric", typeDescription="MySQL Fabric" )

public class MySQLFabricDatabaseMeta extends MySQLDatabaseMeta implements DatabaseInterface {

  public int[] getAccessTypeList() {
    return new int[] { DatabaseMeta.TYPE_ACCESS_NATIVE, DatabaseMeta.TYPE_ACCESS_JNDI };
  }

  public int getDefaultDatabasePort() {
    if ( getAccessType() == DatabaseMeta.TYPE_ACCESS_NATIVE ) {
      return 32274;
    }
    return -1;
  }


  public String getDriverClass() {
   return "com.mysql.fabric.jdbc.FabricMySQLDriver";
  }

  public String getURL(String hostname, String port, String databaseName) {
    if ( Const.isEmpty( port ) ) {
      return "jdbc:mysql:fabric://" + hostname + "/" + databaseName;
    } else {
      return "jdbc:mysql:fabric://" + hostname + ":" + port + "/" + databaseName;
    }
  }

  @Override
  public String getExtraOptionsHelpText() {
    return "https://dev.mysql.com/doc/mysql-utilities/1.4/en/connector-j-fabric-reference-connection-properties.html";
  }

  @Override
  public String[] getUsedLibraries() {
    return new String[] { "mysql-connector-java-5.1.30-bin.jar" };
  }

  @Override
  public boolean supportsRepository() {
    return false;
  }
}
