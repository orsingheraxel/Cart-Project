CREATE TABLE [User] (
  [id] SERIAL PRIMARY KEY,
  [username] VARCHAR(50) UNIQUE NOT NULL,
  [email] VARCHAR(100) UNIQUE NOT NULL,
  [password] VARCHAR(100) NOT NULL,
  [first_name] VARCHAR(50),
  [last_name] VARCHAR(50),
  [address] VARCHAR(255),
  [phone_number] VARCHAR(20),
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP',
  [updated_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

CREATE TABLE [Product] (
  [id] SERIAL PRIMARY KEY,
  [name] VARCHAR(100) NOT NULL,
  [description] TEXT,
  [price] DECIMAL(10,2) NOT NULL,
  [stock_quantity] INT NOT NULL,
  [category] VARCHAR(50),
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP',
  [updated_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

CREATE TABLE [Cart] (
  [id] SERIAL PRIMARY KEY,
  [user_id] INT NOT NULL,
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP',
  [updated_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

CREATE TABLE [CartItem] (
  [id] SERIAL PRIMARY KEY,
  [cart_id] INT NOT NULL,
  [product_id] INT NOT NULL,
  [quantity] INT NOT NULL,
  [price_at_purchase] DECIMAL(10,2) NOT NULL,
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP',
  [updated_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

CREATE TABLE [CartHistory] (
  [id] SERIAL PRIMARY KEY,
  [user_id] INT NOT NULL,
  [total_price] DECIMAL(10,2) NOT NULL,
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

CREATE TABLE [CartHistoryItem] (
  [id] SERIAL PRIMARY KEY,
  [cart_history_id] INT NOT NULL,
  [product_id] INT NOT NULL,
  [quantity] INT NOT NULL,
  [price_at_purchase] DECIMAL(10,2) NOT NULL,
  [created_at] TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP'
)
GO

ALTER TABLE [Cart] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [CartItem] ADD FOREIGN KEY ([cart_id]) REFERENCES [Cart] ([id])
GO

ALTER TABLE [CartItem] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO

ALTER TABLE [CartHistory] ADD FOREIGN KEY ([user_id]) REFERENCES [User] ([id])
GO

ALTER TABLE [CartHistoryItem] ADD FOREIGN KEY ([cart_history_id]) REFERENCES [CartHistory] ([id])
GO

ALTER TABLE [CartHistoryItem] ADD FOREIGN KEY ([product_id]) REFERENCES [Product] ([id])
GO
