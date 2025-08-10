:remote connect tinkerpop.server conf/remote.yaml session
:remote console

println "=== Loading complex dataset ==="

// Locations
ny = g.addV('location').property('city','New York').property('country','USA').property('population',8800000).next();
la = g.addV('location').property('city','Los Angeles').property('country','USA').property('population',3900000).next();
lon = g.addV('location').property('city','London').property('country','UK').property('population',9000000).next();
ber = g.addV('location').property('city','Berlin').property('country','DE').property('population',3700000).next();

// Universities
mit = g.addV('university').property('name','MIT').property('established',1861).property('ranking',1).next();
stan = g.addV('university').property('name','Stanford').property('established',1885).property('ranking',2).next();
ox = g.addV('university').property('name','Oxford').property('established',1096).property('ranking',3).next();

// Companies
tech = g.addV('company').property('name','TechCorp').property('company_type','Public').property('founded',2001).property('employees',5000).property('revenue',2500000000L).property('industry','Software').next();
med = g.addV('company').property('name','MediHealth').property('company_type','Private').property('founded',1998).property('employees',2000).property('revenue',750000000L).property('industry','Healthcare').next();
fin = g.addV('company').property('name','FinNova').property('company_type','Public').property('founded',2010).property('employees',1200).property('revenue',450000000L).property('industry','FinTech').next();

// Projects
apollo = g.addV('project').property('name','Apollo').property('project_type','AI').property('budget',8000000).property('status','active').property('start_date','2024-01-15').next();
hermes = g.addV('project').property('name','Hermes').property('project_type','Cloud').property('budget',5000000).property('status','active').property('start_date','2023-10-01').next();
zephyr = g.addV('project').property('name','Zephyr').property('project_type','Data').property('budget',3000000).property('status','paused').property('start_date','2024-03-01').next();

// Skills
java = g.addV('skill').property('name','Java').property('category','language').property('skill_level','advanced').next();
python = g.addV('skill').property('name','Python').property('category','language').property('skill_level','advanced').next();
cloud = g.addV('skill').property('name','AWS').property('category','cloud').property('skill_level','intermediate').next();
ml = g.addV('skill').property('name','ML').property('category','domain').property('skill_level','advanced').next();

// People
alice = g.addV('person').property('name','Alice').property('email','alice@example.com').property('age',29).property('city','New York').property('bio','Senior engineer working on cloud native systems').property('salary',150000).property('experience_years',7).next();
bob = g.addV('person').property('name','Bob').property('email','bob@example.com').property('age',35).property('city','Los Angeles').property('bio','Architect with a passion for distributed systems and search').property('salary',190000).property('experience_years',12).next();
carol = g.addV('person').property('name','Carol').property('email','carol@example.com').property('age',41).property('city','London').property('bio','Product leader bridging AI research and engineering').property('salary',210000).property('experience_years',17).next();
dave = g.addV('person').property('name','Dave').property('email','dave@example.com').property('age',26).property('city','Berlin').property('bio','Site reliability engineer and DevOps enthusiast').property('salary',120000).property('experience_years',4).next();

// Capture IDs to ensure robust referencing in remote session
nyId = ny.id(); laId = la.id(); lonId = lon.id(); berId = ber.id();
mitId = mit.id(); stanId = stan.id(); oxId = ox.id();
techId = tech.id(); medId = med.id(); finId = fin.id();
apolloId = apollo.id(); hermesId = hermes.id(); zephyrId = zephyr.id();
javaId = java.id(); pythonId = python.id(); cloudId = cloud.id(); mlId = ml.id();
aliceId = alice.id(); bobId = bob.id(); carolId = carol.id(); daveId = dave.id();

// Relationships: locations
g.V(aliceId).addE('located_in').to(__.V(nyId)).property('since',2020).iterate();
g.V(bobId).addE('located_in').to(__.V(laId)).property('since',2020).iterate();
g.V(carolId).addE('located_in').to(__.V(lonId)).property('since',2020).iterate();
g.V(daveId).addE('located_in').to(__.V(berId)).property('since',2020).iterate();

// Relationships: education
g.V(aliceId).addE('studied_at').to(__.V(mitId)).property('degree','MS').property('graduation_year',2018).iterate();
g.V(bobId).addE('studied_at').to(__.V(stanId)).property('degree','MS').property('graduation_year',2010).iterate();
g.V(carolId).addE('studied_at').to(__.V(oxId)).property('degree','PhD').property('graduation_year',2008).iterate();
g.V(daveId).addE('studied_at').to(__.V(mitId)).property('degree','BS').property('graduation_year',2020).iterate();

// Relationships: employment
g.V(aliceId).addE('works_at').to(__.V(techId)).property('position','Engineer').property('since',2019).iterate();
g.V(bobId).addE('works_at').to(__.V(techId)).property('position','Architect').property('since',2016).iterate();
g.V(carolId).addE('works_at').to(__.V(finId)).property('position','VP Product').property('since',2018).iterate();
g.V(daveId).addE('works_at').to(__.V(medId)).property('position','SRE').property('since',2021).iterate();

// Relationships: skills
g.V(aliceId).addE('has_skill').to(__.V(javaId)).property('since',2020).iterate();
g.V(aliceId).addE('has_skill').to(__.V(pythonId)).property('since',2020).iterate();
g.V(aliceId).addE('has_skill').to(__.V(cloudId)).property('since',2021).iterate();
g.V(bobId).addE('has_skill').to(__.V(javaId)).property('since',2012).iterate();
g.V(bobId).addE('has_skill').to(__.V(cloudId)).property('since',2015).iterate();
g.V(bobId).addE('has_skill').to(__.V(mlId)).property('since',2018).iterate();
g.V(carolId).addE('has_skill').to(__.V(pythonId)).property('since',2010).iterate();
g.V(carolId).addE('has_skill').to(__.V(mlId)).property('since',2012).iterate();
g.V(daveId).addE('has_skill').to(__.V(javaId)).property('since',2019).iterate();
g.V(daveId).addE('has_skill').to(__.V(pythonId)).property('since',2020).iterate();

// Relationships: projects collaboration
g.V(aliceId).addE('collaborates_on').to(__.V(apolloId)).property('role_in_project','contributor').iterate();
g.V(aliceId).addE('collaborates_on').to(__.V(hermesId)).property('role_in_project','contributor').iterate();
g.V(bobId).addE('collaborates_on').to(__.V(apolloId)).property('role_in_project','lead').iterate();
g.V(bobId).addE('collaborates_on').to(__.V(zephyrId)).property('role_in_project','contributor').iterate();
g.V(carolId).addE('collaborates_on').to(__.V(apolloId)).property('role_in_project','sponsor').iterate();
g.V(daveId).addE('collaborates_on').to(__.V(hermesId)).property('role_in_project','contributor').iterate();

// Social graph (friendships)
g.V(aliceId).addE('knows').to(__.V(bobId)).property('since',2015).iterate();
g.V(bobId).addE('knows').to(__.V(carolId)).property('since',2012).iterate();
g.V(carolId).addE('knows').to(__.V(daveId)).property('since',2019).iterate();

g.tx().commit();
println "✓ Complex dataset loaded"
