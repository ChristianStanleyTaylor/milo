/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface NodeManager<T extends Node> {

    boolean containsNode(NodeId nodeId);

    Optional<T> addNode(T node);

    Optional<T> getNode(NodeId nodeId);

    Optional<T> getNode(ExpandedNodeId nodeId);

    Optional<T> removeNode(NodeId nodeId);

    Optional<T> removeNode(ExpandedNodeId nodeId);

    void addReference(Reference reference);

    void removeReference(Reference reference);

    List<Reference> getReferences(NodeId sourceNodeId);

    List<Reference> getReferences(NodeId sourceNodeId, Predicate<Reference> filter);

    List<Reference> getReferencesTo(NodeId targetNodeId);

    default boolean containsNode(Node node) {
        return containsNode(node.getNodeId());
    }
    default boolean containsNode(ExpandedNodeId nodeId) {
        return nodeId.local().map(this::containsNode).orElse(false);
    }

    @Nullable
    default T get(NodeId nodeId) {
        return getNode(nodeId).orElse(null);
    }

    @Nullable
    default T get(ExpandedNodeId nodeId) {
        return getNode(nodeId).orElse(null);
    }

}
