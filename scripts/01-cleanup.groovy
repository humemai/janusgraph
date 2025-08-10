:remote connect tinkerpop.server conf/remote.yaml session
:remote console

println "=== General Cleanup (single-pass, single-line statements) ==="

println "Dropping all vertices and edges..."; g.V().drop().iterate(); g.tx().commit(); println "✓ Data dropped"

mgmt = graph.openManagement();
println "Dropping vertex graph indexes..."; mgmt.getGraphIndexes(Vertex.class).each{ idx -> try { mgmt.dropIndex(idx) } catch(e) { println "  skip index: ${idx.name()} (${e.message})" } };
println "Dropping edge graph indexes..."; mgmt.getGraphIndexes(Edge.class).each{ idx -> try { mgmt.dropIndex(idx) } catch(e) { println "  skip index: ${idx.name()} (${e.message})" } };
mgmt.commit(); println "✓ Index removal requested"; Thread.sleep(3000)

mgmt = graph.openManagement();
println "Dropping edge labels..."; mgmt.getRelationTypes(EdgeLabel.class).each{ el -> try { mgmt.dropEdgeLabel(el) } catch(e){ println "  skip edge: ${el.name()} (${e.message})" } };
println "Dropping vertex labels..."; mgmt.getVertexLabels().each{ vl -> try { mgmt.dropVertexLabel(vl) } catch(e){ println "  skip vertex: ${vl.name()} (${e.message})" } };
println "Dropping property keys..."; mgmt.getRelationTypes(PropertyKey.class).each{ pk -> try { mgmt.dropPropertyKey(pk) } catch(e){ println "  skip prop: ${pk.name()} (${e.message})" } };
mgmt.commit(); println "✓ Schema dropped"

println "Verifying..."; println "Vertices: " + g.V().count().next(); println "Edges: " + g.E().count().next()
mgmt = graph.openManagement();
println "Remaining vertex labels: " + mgmt.getVertexLabels().size()
println "Remaining edge labels: " + mgmt.getRelationTypes(EdgeLabel.class).size()
println "Remaining property keys: " + mgmt.getRelationTypes(PropertyKey.class).size()
println "Remaining graph indexes: " + (mgmt.getGraphIndexes(Vertex.class).size() + mgmt.getGraphIndexes(Edge.class).size())
mgmt.rollback()

println "=== Cleanup done ==="