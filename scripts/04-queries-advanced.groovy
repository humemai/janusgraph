:remote connect tinkerpop.server conf/remote.yaml session
:remote console

println "=== Advanced queries ==="

// 1) Basic counts
println "V count: ${g.V().count().next()} E count: ${g.E().count().next()}"

// 2) Composite index lookups
println 'Lookup person by email (composite):'; g.V().hasLabel('person').has('email','alice@example.com').valueMap(true).limit(1).toList().each{ println it };

// 3) Mixed index text search (OpenSearch)
println 'Full-text name contains "ali" and city = New York:'; g.V().hasLabel('person').has('name',textContains('ali')).has('city','New York').project('id','name','city','age').by(id()).by(values('name')).by(values('city')).by(values('age')).toList().each{ println it };

// 4) Range and sort using mixed index fields
println 'Companies founded between 1995 and 2010 sorted by employees desc:'; g.V().hasLabel('company').has('founded',between(1995,2011)).order().by('employees',desc).limit(5).project('name','founded','employees').by(values('name')).by(values('founded')).by(values('employees')).toList().each{ println it };

// 5) Path traversal: friends-of-friends working at same company
println 'Friends-of-friends who work at the same company as Alice:'; g.V().has('person','name','Alice').as('a').out('works_at').aggregate('ac').select('a').out('knows').out('knows').dedup().where(out('works_at').where(within('ac'))).valueMap('name','email').toList().each{ println it };

// 6) Project contributors with ML skill
println 'Project Apollo contributors with ML skill:'; g.V().has('project','name','Apollo').in('collaborates_on').where(out('has_skill').has('name','ML')).valueMap('name','email').toList().each{ println it };

// 7) Aggregation-like patterns: count by city
println 'Count of people by city:'; g.V().hasLabel('person').groupCount().by('city').next().each{ k,v -> println "${k}: ${v}" };

// 8) Verify edge index usage (order by since on knows)
println 'Latest friendships (knowsBySince):'; g.V().hasLabel('person').outE('knows').order().by('since',desc).limit(5).project('from','to','since').by(outV().values('name')).by(inV().values('name')).by(values('since')).toList().each{ println it };

println "✓ Advanced queries done"
