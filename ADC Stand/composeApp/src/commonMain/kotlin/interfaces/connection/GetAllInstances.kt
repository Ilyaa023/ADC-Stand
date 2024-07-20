package interfaces.connection

import models.Connection

expect fun getAllInstances(out: (List<Connection>) -> Unit)